using System;

public class Person : IComparable<Person>
{
    public string Email { get; set; }
    public string Name { get; set; }
    public int Age { get; set; }
    public string Town { get; set; }

    public Person(string name, string email, string town, int age)
    {
        this.Name = name;
        this.Email = email;
        this.Town = town;
        this.Age = age;
    }

    public int CompareTo(Person other)
    {
        return this.Email.CompareTo(other.Email);
    }
}
