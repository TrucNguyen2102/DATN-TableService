package com.business.table_service.service;


import com.business.table_service.dto.TablePlayInfo;
import com.business.table_service.dto.TablePlayWithPriceDTO;
import com.business.table_service.dto.TableStatusUpdateRequest;
import com.business.table_service.entity.Price;
import com.business.table_service.entity.TablePlay;
import com.business.table_service.exception.ResourceNotFoundException;
import com.business.table_service.repository.PriceRepo;
import com.business.table_service.repository.TablePlayRepo;
import com.business.table_service.repository.TypePriceRepo;
import com.business.table_service.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TablePlayServiceImpl implements TablePlayService{
    @Autowired
    private TablePlayRepo tablePlayRepo;

    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    private TypePriceRepo typePriceRepo;

    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private PriceService priceService;
    @Override
    public List<TablePlay> getAllTables() {
        return tablePlayRepo.findAll();
    }

    // Phương thức phân trang để lấy các bàn
    public Page<TablePlay> getAllTables(Pageable pageable) {
        return tablePlayRepo.findAll(pageable);  // Sử dụng phương thức findAll() của JpaRepository với Pageable
    }


    @Override
    public TablePlay addTablePlay(TablePlay tablePlay) {
        return tablePlayRepo.save(tablePlay);
    }

    @Override
    public TablePlay updateTable(Integer id, TablePlay newTablePlay) {
        TablePlay existingTable = tablePlayRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));

        existingTable.setTableNum(newTablePlay.getTableNum());
        existingTable.setTableStatus(newTablePlay.getTableStatus());
        existingTable.setType(newTablePlay.getType());

        return tablePlayRepo.save(existingTable);
    }

    public void deleteTable(Integer id) {
        if (tablePlayRepo.existsById(id)) {
            tablePlayRepo.deleteById(id);
        } else {
            throw new RuntimeException("Bàn không tồn tại.");
        }
    }

    public List<TablePlayWithPriceDTO> getAllTablesWithPrices() {
        List<TablePlay> tables = tablePlayRepo.findAll();
        List<TablePlayWithPriceDTO> tableWithPrices = new ArrayList<>();

        for (TablePlay table : tables) {
            List<Integer> priceIds = table.getType().getPriceIds(); // Lấy danh sách priceId từ loại
            if (!priceIds.isEmpty()) {
                Integer priceId = priceIds.get(0); // Giả sử bạn lấy priceId đầu tiên
                Price price = priceRepo.getPriceById(priceId); // Lấy thông tin giá dựa trên priceId

                // Kiểm tra giá có khác null không trước khi thêm vào danh sách DTO
                if (price != null) {
                    tableWithPrices.add(new TablePlayWithPriceDTO(table.getId(), table.getTableNum(), table.getTableStatus(), table.getType().getName(), price.getPrice())); // Lấy giá thực tế
                }
            }
        }

        return tableWithPrices;
    }

    public Optional<TablePlay> findById(Integer id) {
        return tablePlayRepo.findById(id);
    }

//    public boolean updateTableStatus(Integer tableId, String status) {
//        Optional<TablePlay> tableOptional = tablePlayRepo.findById(tableId);
//        if (tableOptional.isPresent()) {
//            TablePlay tablePlay = tableOptional.get();
//            tablePlay.setTableStatus(status);
//            tablePlayRepo.save(tablePlay);
//            return true;
//        }
//        return false;
//    }

    // Cập nhật trạng thái của bàn theo tableId
    public TablePlay updateTableStatus(Integer tableId, String tableStatus) {
        // Tìm bàn theo ID
        TablePlay table = tablePlayRepo.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id " + tableId));

        // Cập nhật trạng thái bàn
        table.setTableStatus(tableStatus);
        return tablePlayRepo.save(table); // Lưu bàn với trạng thái mới
    }

    public TablePlayWithPriceDTO getTableWithPriceById(Integer id) {
        TablePlay table = tablePlayRepo.findById(id).orElse(null);
        if (table != null) {
            List<Integer> priceIds = table.getType().getPriceIds();
            if (!priceIds.isEmpty()) {
                Integer priceId = priceIds.get(0);
                Price price = priceRepo.getPriceById(priceId);
                if (price != null) {
                    return new TablePlayWithPriceDTO(table.getId(), table.getTableNum(), table.getTableStatus(), table.getType().getName(), price.getPrice());
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy bàn
    }

    public List<TablePlayWithPriceDTO> getTableDetails(List<Integer> tableIds) {
        List<TablePlayWithPriceDTO> tableWithPrices = new ArrayList<>();

        for (Integer tableId : tableIds) {
            TablePlay table = tablePlayRepo.findById(tableId).orElse(null);
            if (table != null) {
                List<Integer> priceIds = table.getType().getPriceIds();
                if (!priceIds.isEmpty()) {
                    Integer priceId = priceIds.get(0); // Giả sử chỉ lấy giá đầu tiên
                    Price price = priceRepo.getPriceById(priceId);
                    if (price != null) {
                        tableWithPrices.add(new TablePlayWithPriceDTO(table.getId(), table.getTableNum(), table.getTableStatus(), table.getType().getName(), price.getPrice()));
                    }
                }
            }
        }

        return tableWithPrices;
    }


    public List<TablePlay> getTablesByStatus(String status) {
        return tablePlayRepo.findByTableStatus(status);
    }

    // Phương thức này sẽ trả về trạng thái của bàn dựa trên tableId
    public String getTableStatus(Integer tableId) {
        // Tìm bàn theo tableId trong cơ sở dữ liệu
        Optional<TablePlay> tablePlay = tablePlayRepo.findById(tableId);

        // Kiểm tra nếu bàn tồn tại
        if (tablePlay.isPresent()) {
            return tablePlay.get().getTableStatus();  // Trả về trạng thái của bàn
        } else {
            return "Bàn không tồn tại";  // Trả về thông báo nếu bàn không tồn tại
        }
    }

    // Hàm tìm bàn dựa trên ID
    public TablePlay getTableById(Integer tableId) {
        return tablePlayRepo.findById(tableId).orElse(null);
    }

}
