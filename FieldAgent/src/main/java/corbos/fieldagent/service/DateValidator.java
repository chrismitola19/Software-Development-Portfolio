///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package corbos.fieldagent.service;
//
//import java.time.LocalDate;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author chris
// */
//@Component
//public class DateValidator implements ConstraintValidator<OlderThanTen, LocalDate>{
//   
//    private int age;
// 
//    @Override
//    public void initialize(OlderThanTen age) {
//        this.age = age.value();
//    }
//   
//    @Override
//    public boolean isValid(LocalDate birthDateYear, ConstraintValidatorContext context) {
//        if (birthDateYear == null) {
//            return false;
//        }
//        return LocalDate.now().getYear() - birthDateYear.getYear() > age;
//    }
//}



//ended up using html