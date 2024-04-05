package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.services.ExcelUploadService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {
    private final PasswordEncoder passwordEncoder;

    public ExcelUploadServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }

    @Override
    public List<User> getEmployeeDataFromExcel(InputStream inputStream) {
        List<User> user_data = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("master");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                User user = new User();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 : user.setId((long) cell.getNumericCellValue());
                        case 1 : user.setDivision(cell.getStringCellValue());
                        case 2 : user.setStaffId(cell.getStringCellValue());
                        case 3 : user.setName(cell.getStringCellValue());
                        case 4 : user.setDoorLogNum((long) cell.getNumericCellValue());
                        case 5 : user.setDept(cell.getStringCellValue());
                        case 6 : user.setTeam(cell.getStringCellValue());
                        case 7 : user.setEmail(cell.getStringCellValue());


                        default : {
                        }
                    }
                    cellIndex++;
                }
                user.setPassword(passwordEncoder.encode("DAT@123"));
                user.setRole(User.Role.USER);
                user.setActive(true);
                user_data.add(user);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return user_data;
    }
}
