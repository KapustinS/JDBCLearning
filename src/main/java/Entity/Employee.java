package Entity;

import java.sql.Date;
import java.util.Objects;

public class Employee {

    private Long id;
    private String firstName;
    private String LastName;
    private Date birthday;
    private Long addressID;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(LastName, employee.LastName) && Objects.equals(birthday, employee.birthday) && Objects.equals(addressID, employee.addressID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, LastName, birthday, addressID);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", birthday=" + birthday +
                ", addressID=" + addressID +
                '}';
    }
}
