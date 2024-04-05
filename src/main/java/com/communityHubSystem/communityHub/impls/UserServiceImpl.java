package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.services.ExcelUploadService;
import com.communityHubSystem.communityHub.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ExcelUploadService excelUploadService;

    public UserServiceImpl(UserRepository userRepository, ExcelUploadService excelUploadService) {
        this.userRepository = userRepository;
        this.excelUploadService = excelUploadService;
    }

    @Override
    public Optional<User> findByStaffId(String staffId) {
        return userRepository.findByStaffId(staffId);
    }
    @Override
    public void saveUserToDatabase(MultipartFile file){
        if(excelUploadService.isValidExcelFile(file)){
            try {
                List<User> users = excelUploadService.getEmployeeDataFromExcel(file.getInputStream());
                this.userRepository.saveAll(users);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
