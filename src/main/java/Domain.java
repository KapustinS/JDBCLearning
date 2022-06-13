import Entity.Address;
import Entity.EmplProj;
import Entity.Employee;
import Entity.Project;
import bl.Util;
import service.AddressService;
import service.EmplProjService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Domain {

    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();
        EmplProjService emplProjService = new EmplProjService();

        Address address = new Address();
        address.setId(1L);
        address.setCountry("DC");
        address.setCity("Gotham City");
        address.setStreet("Arkham street 1");
        address.setPostcode("12345");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1939, Calendar.MAY, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddressID(address.getId());

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Gotham City Department Commissioner");

        EmplProj emplProj = new EmplProj();
        emplProj.setEmployeeID(employee.getId());
        emplProj.setProjectID(project.getId());


//        addressService.add(address);
//        employeeService.add(employee);
//        projectService.add(project);
//        emplProjService.add(emplProj);

        List<Address> addressList = addressService.getAll();
        for (Address a:addressList){
            System.out.println(a);
        }
        List<Employee> employeeList = employeeService.getAll();
        for(Employee e:employeeList){
            System.out.println(e);
        }
    }
}
