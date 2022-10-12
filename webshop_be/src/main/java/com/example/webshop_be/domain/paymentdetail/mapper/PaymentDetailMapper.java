package com.example.webshop_be.domain.paymentdetail.mapper;

import com.example.webshop_be.domain.paymentdetail.PaymentDetail;
import com.example.webshop_be.domain.paymentdetail.PaymentDetailDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentDetailMapper {
    PaymentDetail fromDTO(PaymentDetailDTO paymentDetailDTO);

    List<PaymentDetail> fromDTOs(List<PaymentDetailDTO> paymentDetailDTOS);

    PaymentDetailDTO toDTO(PaymentDetail paymentDetail);

    List<PaymentDetailDTO> toDTOs(List<PaymentDetail> paymentDetails);
}
