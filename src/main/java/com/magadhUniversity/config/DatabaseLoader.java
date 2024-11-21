//package com.magadhUniversity.config;
//
//import com.magadhUniversity.model.Employee;
//import com.magadhUniversity.model.Role;
//import com.magadhUniversity.model.Student;
//import com.magadhUniversity.model.User;
//import com.magadhUniversity.repository.EmployeeRepository;
//import com.magadhUniversity.repository.RoleRepository;
//import com.magadhUniversity.repository.StudentRepository;
//import com.magadhUniversity.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.boot.CommandLineRunner;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class DatabaseLoader {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Bean
//    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
//        return args -> {
//            // Create roles if not exist
//            if (roleRepository.findByName("ADMIN") == null) {
//                Role adminRole = new Role();
//                adminRole.setName("ADMIN");
//                roleRepository.save(adminRole);
//            }
//
//            if (roleRepository.findByName("EMPLOYEE") == null) {
//                Role employeeRole = new Role();
//                employeeRole.setName("EMPLOYEE");
//                roleRepository.save(employeeRole);
//            }
//
//            if (roleRepository.findByName("STUDENT") == null) {
//                Role studentRole = new Role();
//                studentRole.setName("STUDENT");
//                roleRepository.save(studentRole);
//            }
//
//            // Create admin user if not exist
//            if (userRepository.findByUsername("admin") == null) {
//                User admin = new User();
//                admin.setUsername("admin");
//                admin.setPassword(passwordEncoder.encode("admin123"));
//                Set<Role> adminRoles = new HashSet<>();
//                adminRoles.add(roleRepository.findByName("ADMIN"));
//                admin.setRoles(adminRoles);
//                userRepository.save(admin);
//            }
//
//            // Register existing employees with default password
//            List<Employee> employees = employeeRepository.findAll();
//            for (Employee employee : employees) {
//                if (employee.getUser() == null) {
//                    User user = new User();
//                    user.setUsername(employee.getEmail());
//                    user.setPassword(passwordEncoder.encode("defaultPassword"));
//                    user.setRoles(Set.of(roleRepository.findByName("EMPLOYEE")));
//                    userRepository.save(user);
//                    employee.setUser(user);
//                    employeeRepository.save(employee);
//                }
//            }
//
//            // Register existing students with default password
//            List<Student> students = studentRepository.findAll();
//            for (Student student : students) {
//                if (student.getUser() == null) {
//                    User user = new User();
//                    user.setUsername(student.getEmail());
//                    user.setPassword(passwordEncoder.encode("defaultPassword"));
//                    user.setRoles(Set.of(roleRepository.findByName("STUDENT")));
//                    userRepository.save(user);
//                    student.setUser(user);
//                    studentRepository.save(student);
//                }
//            }
//        };
//    }
//}

package com.magadhUniversity.config;

import com.magadhUniversity.model.Employee;
import com.magadhUniversity.model.Role;
import com.magadhUniversity.model.Student;
import com.magadhUniversity.model.User;
import com.magadhUniversity.repository.EmployeeRepository;
import com.magadhUniversity.repository.RoleRepository;
import com.magadhUniversity.repository.StudentRepository;
import com.magadhUniversity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DatabaseLoader {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            // Create roles if not exist
            if (roleRepository.findByName("ADMIN") == null) {
                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                roleRepository.save(adminRole);
            }

            if (roleRepository.findByName("EMPLOYEE") == null) {
                Role employeeRole = new Role();
                employeeRole.setName("EMPLOYEE");
                roleRepository.save(employeeRole);
            }

            if (roleRepository.findByName("STUDENT") == null) {
                Role studentRole = new Role();
                studentRole.setName("STUDENT");
                roleRepository.save(studentRole);
            }

            // Create admin user if not exist
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(roleRepository.findByName("ADMIN"));
                admin.setRoles(adminRoles);
                userRepository.save(admin);
            }

            // Register existing employees with prefixed IDs
            List<Employee> employees = employeeRepository.findAll();
            for (Employee employee : employees) {
                if (employee.getUser() == null) {
                    User user = new User();
                    user.setUsername("employee" + employee.getEmployeeId());
                    user.setPassword(passwordEncoder.encode("defaultPassword"));
                    user.setRoles(Set.of(roleRepository.findByName("EMPLOYEE")));
                    userRepository.save(user);
                    employee.setUser(user);
                    employeeRepository.save(employee);
                }
            }

            // Register existing students with prefixed IDs
            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                if (student.getUser() == null) {
                    User user = new User();
                    user.setUsername("student" + student.getStudentId());
                    user.setPassword(passwordEncoder.encode("defaultPassword"));
                    user.setRoles(Set.of(roleRepository.findByName("STUDENT")));
                    userRepository.save(user);
                    student.setUser(user);
                    studentRepository.save(student);
                }
            }
        };
    }
}
