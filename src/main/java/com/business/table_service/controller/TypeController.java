package com.business.table_service.controller;

import com.business.table_service.dto.TablePlayWithPriceDTO;
import com.business.table_service.dto.TypeDTO;
import com.business.table_service.entity.Type;
import com.business.table_service.repository.PriceRepo;
import com.business.table_service.service.PriceService;
import com.business.table_service.service.TablePlayService;
import com.business.table_service.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private TablePlayService tablePlayService;

    @Autowired
    private PriceRepo priceRepo;

    // Thêm một loại bàn mới với danh sách các giá liên kết
    @PostMapping("/types/add")
    public ResponseEntity<Type> addType(@RequestBody TypeDTO typeDTO) {
        try {
            Type createdType = typeService.addType(typeDTO);
            return ResponseEntity.ok(createdType);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    // API lấy danh sách loại bàn
    @GetMapping("/types/all")
    public List<TypeDTO> getAllTypes() {
        List<Type> types = typeService.getAllTypes(); // Giả sử bạn có một service để lấy dữ liệu
        return types.stream()
                .map(type -> new TypeDTO(type.getId(), type.getName(), type.getPriceIds()))
                .collect(Collectors.toList());
    }










    //API cập nhật loại bàn
    // Cập nhật một loại bàn với danh sách các giá mới
    @PutMapping("/types/update/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Integer id, @RequestBody TypeDTO typeDTO) {
        try {
            Type updatedType = typeService.updateType(id, typeDTO);
            return ResponseEntity.ok(updatedType);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    //kiểm tra loại bàn trc khi xóa
    @GetMapping("/types/check-used/{id}")
    public ResponseEntity<Map<String, Boolean>> checkTypeUsed(@PathVariable Integer id) {
        try {
            boolean isUsed = tablePlayService.isTypeUsed(id); // Giả sử tableService sẽ kiểm tra loại bàn trong các bàn chơi
            Map<String, Boolean> response = new HashMap<>();
            response.put("isUsed", isUsed);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    //API xóa loại
    // Xóa một loại bàn
    @DeleteMapping("/types/delete/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Integer id) {
        try {
            typeService.deleteType(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/types/{typeId}")
//    public ResponseEntity<TypeDTO> getTypeById(@PathVariable Integer typeId) {
//        TypeDTO typeDTO = typeService.getTypeById(typeId);
//        return ResponseEntity.ok(typeDTO);
//    }





}
