package com.lyc.dto;

import com.lyc.domain.Setmeal;
import com.lyc.domain.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
