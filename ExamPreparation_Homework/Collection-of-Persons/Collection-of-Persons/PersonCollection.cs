using System.Collections.Generic;
using System.Linq;
using Wintellect.PowerCollections;

public class PersonCollection : IPersonCollection
{
    private IDictionary<string, Person> peoplesByEmail;
    private IDictionary<string, OrderedSet<Person>> peoplesByEmailDomain;
    private IDictionary<string, OrderedSet<Person>> peoplesByNameAndTown;
    private OrderedDictionary<int, OrderedSet<Person>> peoplesByAge;
    private IDictionary<string, OrderedDictionary<int, OrderedSet<Person>>> peoplesByAgeAndTown;

    public PersonCollection()
    {
        this.peoplesByEmail = new Dictionary<string, Person>();
        this.peoplesByEmailDomain = new Dictionary<string, OrderedSet<Person>>();
        this.peoplesByNameAndTown = new Dictionary<string, OrderedSet<Person>>();
        this.peoplesByAge = new OrderedDictionary<int, OrderedSet<Person>>();
        this.peoplesByAgeAndTown = new Dictionary<string, OrderedDictionary<int, OrderedSet<Person>>>();
    }

    public bool AddPerson(string email, string name, int age, string town)
    {
        if (this.peoplesByEmail.ContainsKey(email))
        {
            return false;
        }

        var person = new Person(name, email, town, age);
        this.peoplesByEmail[email] = person;

        var ageAndTownKey = this.CombineKey(name, town);
        this.peoplesByNameAndTown.AppendValueToKey(ageAndTownKey, person);

        var domain = this.ExtractDomain(email);
        this.peoplesByEmailDomain.AppendValueToKey(domain, person);

        this.peoplesByAge.AppendValueToKey(age, person);

        this.peoplesByAgeAndTown.EnsureKeyExists(town);
        this.peoplesByAgeAndTown[town].AppendValueToKey(age, person);

        return true;
    }


    public int Count 
    {
        get
        {
            return this.peoplesByEmail.Count;
        }
    }

    public Person FindPerson(string email)
    {
        if (!this.peoplesByEmail.ContainsKey(email))
        {
            return null;
        }

        var person = this.peoplesByEmail[email];
        return person;
    }

    public bool DeletePerson(string email)
    {
        if (!this.peoplesByEmail.ContainsKey(email))
        {
            return false;
        }

        var person = this.peoplesByEmail[email];
        this.peoplesByEmail.Remove(email);

        var domain = this.ExtractDomain(email);
        this.peoplesByEmailDomain[domain].Remove(person);

        var key = this.CombineKey(person.Name, person.Town);
        this.peoplesByNameAndTown[key].Remove(person);

        this.peoplesByAge[person.Age].Remove(person);

        this.peoplesByAgeAndTown[person.Town][person.Age].Remove(person);

        return true;
    }

    public IEnumerable<Person> FindPersons(string emailDomain)
    {
        return this.peoplesByEmailDomain.ContainsKey(emailDomain) ?
            this.peoplesByEmailDomain[emailDomain] : Enumerable.Empty<Person>();
    }

    public IEnumerable<Person> FindPersons(string name, string town)
    {
        var nameAndTownKey = this.CombineKey(name, town);
        return this.peoplesByNameAndTown.ContainsKey(nameAndTownKey) ?
            this.peoplesByNameAndTown[nameAndTownKey] : Enumerable.Empty<Person>();
    }

    public IEnumerable<Person> FindPersons(int startAge, int endAge)
    {
        return this.peoplesByAge.Range(startAge, true, endAge, true).SelectMany(x => x.Value);
    }

    public IEnumerable<Person> FindPersons(
        int startAge, int endAge, string town)
    {
        if (!this.peoplesByAgeAndTown.ContainsKey(town))
        {
            return Enumerable.Empty<Person>();
        }

        return this.peoplesByAgeAndTown[town].Range(startAge, true, endAge, true).SelectMany(x => x.Value);
    }


    private string CombineKey(string key1, string key2)
    {
        return key1 + key2;
    }

    private string ExtractDomain(string email)
    {
        return email.Split('@')[1];
    }
}
