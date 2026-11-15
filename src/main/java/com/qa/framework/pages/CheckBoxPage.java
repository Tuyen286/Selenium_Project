package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CheckBoxPage extends BasePage {
    private static final String url = ConfigManager.getBaseUrl() + "/checkboxes";
    private static final Logger log = LogManager.getLogger(CheckBoxPage.class);

    private By checkbox (int index){
        return By.xpath("(//input[@type='checkbox'])[" + index + "]");
    }

    public CheckBoxPage open(){
        DriverManager.openUrl(url);
        return this;
    }

    public void selectCheckbox(int index){
        if(!isCheckboxSelected(index)){
            click(checkbox(index));
        }
        else log.info("Checkbox {} has already been checked", index);
    }

    public void unSelectCheckbox(int index){
        if(isCheckboxSelected(index)){
            click(checkbox(index));
        }
        else log.info("Checkbox {} has already been unchecked", index);
    }

    public boolean isCheckboxSelected(int index){
        return isSelected(checkbox(index));
    }
}
