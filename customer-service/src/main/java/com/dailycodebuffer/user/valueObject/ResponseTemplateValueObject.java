package com.dailycodebuffer.user.valueObject;
import com.dailycodebuffer.user.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateValueObject {
    private Customer customer;
    private Department department;
}
