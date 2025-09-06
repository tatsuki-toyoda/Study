package com.example.demo.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;


@Data
public class WtsForm {

    
    private Integer id;

    
    @NotEmpty(message = "サーバー名を入力してください")
    @Size(max = 50, message = "サーバー名は50文字以内で入力してください")
    private String serverName;

    
    @NotEmpty(message = "メーカーを入力してください")
    @Size(max = 50, message = "メーカーは50文字以内で入力してください")
    private String maker;

    
    @Min(value = 1, message = "値段は1以上で入力してください")
    private Integer price;
}