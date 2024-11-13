package com.business.table_service.service;

import com.business.table_service.dto.TypeDTO;
import com.business.table_service.entity.Price;
import com.business.table_service.entity.Type;
import com.business.table_service.entity.TypePrice;
import com.business.table_service.entity.TypePriceId;
import com.business.table_service.exception.ResourceNotFoundException;
import com.business.table_service.repository.PriceRepo;
import com.business.table_service.repository.TypePriceRepo;
import com.business.table_service.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private TypePriceRepo typePriceRepo;

//    @Override
//    public Type addType(Type type) {
//        if (isTypeNameExists(type.getName(), null)) {
//            throw new IllegalArgumentException("Tên loại đã tồn tại"); // Kiểm tra tên loại
//        }
//        return typeRepo.save(type);
//    }

    @Override
    public Type addType(TypeDTO typeDTO) {
        // Kiểm tra trùng tên
        if (typeRepo.existsByName(typeDTO.getName())) {
            throw new RuntimeException("Tên loại bàn đã tồn tại");
        }

        Type type = new Type();
        type.setName(typeDTO.getName());

        // Lưu Type vào cơ sở dữ liệu để lấy ID
        Type savedType = typeRepo.save(type);

        // Lấy danh sách giá dựa trên priceIds
        List<Price> selectedPrices = priceRepo.findAllById(typeDTO.getPriceIds());
        if (selectedPrices.size() != typeDTO.getPriceIds().size()) {
            throw new RuntimeException("Một hoặc nhiều giá không tồn tại");
        }

        Set<TypePrice> typePrices = new HashSet<>();
        for (Price price : selectedPrices) {
            TypePriceId typePriceId = new TypePriceId();
            typePriceId.setTypeId(type.getId());  // set typeId cho TypePriceId
            typePriceId.setPriceId(price.getId());  // set priceId cho TypePriceId

            TypePrice typePrice = new TypePrice();
            typePrice.setId(typePriceId);  // thiết lập id cho TypePrice
            typePrice.setType(type);  // thiết lập quan hệ với Type
            typePrice.setPrice(price);  // thiết lập quan hệ với Price
            typePrices.add(typePrice);
        }

        // Lưu danh sách TypePrice vào cơ sở dữ liệu
        typePriceRepo.saveAll(typePrices); // Lưu tất cả TypePrice

        // Cập nhật lại Type với danh sách TypePrice mới
        savedType.setTypePrices(typePrices);  // thiết lập các TypePrice cho Type

        return savedType;  // trả về Type đã lưu
    }


    @Override
    public List<Type> getAllTypes() {
        return typeRepo.findAll();
    }

//    @Transactional
//    public Type updateType(Integer id, TypeDTO typeDTO) {
//        Optional<Type> optionalType = typeRepo.findById(id);
//        if (!optionalType.isPresent()) {
//            throw new RuntimeException("Loại bàn không tồn tại");
//        }
//
//        Type type = optionalType.get();
//        type.setName(typeDTO.getName());
//
//        // Xóa các mối quan hệ hiện tại
//        typePriceRepo.deleteAll(type.getTypePrices());
//        type.getTypePrices().clear();
//
//        // Lấy danh sách giá mới
//        List<Price> selectedPrices = priceRepo.findAllById(typeDTO.getPriceIds());
//        if (selectedPrices.size() != typeDTO.getPriceIds().size()) {
//            throw new RuntimeException("Một hoặc nhiều giá không tồn tại");
//        }
//
//        Set<TypePrice> newTypePrices = new HashSet<>();
//        for (Price price : selectedPrices) {
//            TypePrice typePrice = new TypePrice();
//            typePrice.setType(type);
//            typePrice.setPrice(price);
//
//            // Thiết lập ID cho TypePriceId
//            TypePriceId typePriceId = new TypePriceId(type.getId(), price.getId());
//            typePrice.setId(typePriceId); // Thiết lập ID cho TypePrice
//
//            newTypePrices.add(typePrice);
//        }
//
//        type.setTypePrices(newTypePrices);
//
//        return typeRepo.save(type);
//    }

    @Transactional
    public Type updateType(Integer id, TypeDTO typeDTO) {
        Optional<Type> optionalType = typeRepo.findById(id);
        if (!optionalType.isPresent()) {
            throw new RuntimeException("Loại bàn không tồn tại");
        }

        Type type = optionalType.get();
        type.setName(typeDTO.getName());

        // Xóa từng phần tử trong tập hợp `typePrices` thay vì xóa toàn bộ
        type.getTypePrices().forEach(typePriceRepo::delete);
        type.getTypePrices().clear();

        List<Price> selectedPrices = priceRepo.findAllById(typeDTO.getPriceIds());
        if (selectedPrices.size() != typeDTO.getPriceIds().size()) {
            throw new RuntimeException("Một hoặc nhiều giá không tồn tại");
        }

        for (Price price : selectedPrices) {
            TypePrice typePrice = new TypePrice();
            typePrice.setType(type);
            typePrice.setPrice(price);
            typePrice.setId(new TypePriceId(type.getId(), price.getId()));

            // Thêm mới `typePrice` vào `typePrices`
            type.getTypePrices().add(typePrice);
        }

        return typeRepo.save(type);
    }


    @Override
    public void deleteType(Integer id) {
        if (!typeRepo.existsById(id)) {
            throw new ResourceNotFoundException("Loại không tồn tại với ID: " + id);
        }
        typeRepo.deleteById(id);
    }

//    private boolean isTypeNameExists(String name, Integer id) {
//        List<Type> types = typeRepo.findAll(); // Lấy tất cả các loại bàn
//        return types.stream()
//                .filter(type -> !type.getId().equals(id)) // Loại bỏ loại đang được cập nhật
//                .anyMatch(type -> type.getName().equalsIgnoreCase(name)); // Kiểm tra không phân biệt hoa thường
//    }

    public TypeDTO getTypeById(Integer typeId) {
        Optional<Type> typeOptional = typeRepo.findById(typeId);
        if (typeOptional.isPresent()) {
            Type type = typeOptional.get();
            return new TypeDTO(type.getId(), type.getName(), type.getPriceIds());
        } else {
            throw new ResourceNotFoundException("Type not found with id: " + typeId);
        }
    }


}
