package com.business.table_service.controller;

import com.business.table_service.dto.*;
import com.business.table_service.entity.TablePlay;
import com.business.table_service.entity.Type;
import com.business.table_service.exception.ResourceNotFoundException;
import com.business.table_service.repository.TablePlayRepo;
import com.business.table_service.repository.TypeRepo;
import com.business.table_service.service.TablePlayService;
import com.business.table_service.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class TablePlayController {
    @Autowired
    private TablePlayService tablePlayService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeRepo typeRepo;
    @Autowired
    private TablePlayRepo tablePlayRepo;

    @PostMapping("/add")
    public ResponseEntity<TablePlay> addTable(@RequestBody TablePlayDTO tablePlayDTO) {
        try {
            TablePlay tablePlay = new TablePlay();
            tablePlay.setTableNum(tablePlayDTO.getTableNum());
            tablePlay.setTableStatus(tablePlayDTO.getTableStatus());

            // Thiết lập type từ typeId
            Type type = new Type();
            type.setId(tablePlayDTO.getTypeId()); // Gán type từ ID truyền từ frontend
            tablePlay.setType(type);

            TablePlay savedTable = tablePlayService.addTablePlay(tablePlay);
            return new ResponseEntity<>(savedTable, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/pages/all")
    public ResponseEntity<Page<TablePlay>> getAllTables(Pageable pageable) {
        try {
            // Lấy danh sách bàn theo phân trang
            Page<TablePlay> tablePlays = tablePlayService.getAllTables(pageable);

            if (tablePlays.isEmpty()) {
                return ResponseEntity.noContent().build();  // Nếu danh sách rỗng, trả về mã 204 (No Content)
            }
            return ResponseEntity.ok(tablePlays);  // Trả về danh sách bàn kèm theo thông tin phân trang với mã 200 (OK)
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Trả về lỗi 500 (Internal Server Error)
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TablePlay>> getAllTables() {
        try {
            List<TablePlay> tablePlays = tablePlayService.getAllTables();
            if (tablePlays.isEmpty()) {
                return ResponseEntity.noContent().build(); // Nếu danh sách rỗng, trả về mã 204 (No Content)
            }
            return ResponseEntity.ok(tablePlays); // Trả về danh sách giá kèm mã 200 (OK)
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Trả về lỗi 500 (Internal Server Error)
        }

    }


    @GetMapping("/with-tableNum-typeName")
    public ResponseEntity<List<Map<String, Object>>> getTablesWithNumAndType() {
        try {
            List<TablePlay> tablePlays = tablePlayService.getAllTables();

            List<Map<String, Object>> tableData = tablePlays.stream().map(table -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", table.getId());  // Thêm id của table
                map.put("tableNum", table.getTableNum());
                map.put("typeName", table.getType().getName());  // typeName lấy từ bảng liên kết (Type)
                return map;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(tableData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/with-prices")
    public List<TablePlayWithPriceDTO> getAllTablesWithPrices() {
        try {
            return tablePlayService.getAllTablesWithPrices(); // Gọi dịch vụ để lấy danh sách bàn cùng với giá
        } catch (Exception e) {
            e.printStackTrace(); // Ghi lại lỗi trong console
            return (List<TablePlayWithPriceDTO>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TablePlay> updateTable(@PathVariable Integer id, @RequestBody TablePlayDTO tablePlayDTO) {
        TablePlay newTable = new TablePlay();
        newTable.setTableNum(tablePlayDTO.getTableNum());
        newTable.setTableStatus(tablePlayDTO.getTableStatus());

        Type type = new Type();
        type.setId(tablePlayDTO.getTypeId());
        newTable.setType(type);

        TablePlay updatedTable = tablePlayService.updateTable(id, newTable);
        return new ResponseEntity<>(updatedTable, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable Integer id) {
        try {
            tablePlayService.deleteTable(id);
            return ResponseEntity.ok("Bàn đã được xóa thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi xóa bàn: " + e.getMessage());
        }
    }


    @PutMapping("/update-status")
    public ResponseEntity<String> updateTableStatus(@RequestBody Map<String, Object> request) {
        try {
            // Lấy tableId từ request và chuyển thành Integer
            Object tableIdObject = request.get("tableId");
            Integer tableId = null;

            if (tableIdObject instanceof String) {
                // Nếu là String, chuyển sang Integer
                try {
                    tableId = Integer.parseInt((String) tableIdObject);
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("ID bàn không hợp lệ.");
                }
            } else if (tableIdObject instanceof Integer) {
                // Nếu là Integer, gán trực tiếp
                tableId = (Integer) tableIdObject;
            }

            // Lấy trạng thái từ request
            String status = (String) request.get("status");

            // Kiểm tra tính hợp lệ của các tham số
            if (tableId == null || status == null || status.isEmpty()) {
                return ResponseEntity.badRequest().body("Thông tin bàn hoặc trạng thái không hợp lệ.");
            }

            Optional<TablePlay> tableOptional = tablePlayRepo.findById(tableId);
            if (tableOptional.isPresent()) {
                TablePlay table = tableOptional.get();
                table.setTableStatus(status); // Cập nhật trạng thái bàn
                tablePlayRepo.save(table);


                return ResponseEntity.ok("Trạng thái bàn đã được cập nhật thành công.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bàn không tồn tại.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // API để lấy thông tin bàn theo ID cùng với giá
    @GetMapping("/with-type-price/{id}")
    public ResponseEntity<TablePlayWithPriceDTO> getTableWithPrice(@PathVariable Integer id) {
        try {
            TablePlayWithPriceDTO tableWithPrice = tablePlayService.getTableWithPriceById(id);
            if (tableWithPrice != null) {
                return ResponseEntity.ok(tableWithPrice);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/available")
    public List<TablePlay> getAvailableTables() {
        return tablePlayService.getTablesByStatus("Trống");  // Trả về các bàn có trạng thái "Trống"
    }

}
