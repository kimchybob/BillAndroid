package com.backend.Controller;

import com.backend.Service.AddressService;
import com.backend.Util.AddressResult;
import com.backend.Util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//=@ResponseBody + @Controller
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/address/{sid}")
    public AjaxResult getAddressBySid(@PathVariable("sid") Integer sid){
        if(sid==0)
            return AjaxResult.error("Input Empty");
        List<AddressResult> addressResult = addressService.getAddressBySid(sid);
        if(addressResult.size()==0){
            return AjaxResult.warn("No corresponding building information for this subject.");
        }
        return AjaxResult.success(addressResult);
    }
}
