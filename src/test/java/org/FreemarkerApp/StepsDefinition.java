package org.FreemarkerApp;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getElement;
import static com.codeborne.selenide.Selenide.open;

public class StepsDefinition {


    @Если("^я прошел по ссылке '(.+)'")
    public void goToLink(String link) {
        open(link);
    }

    @То("^откроется форма с таблицами:$")
    public void checkOnLoad(List<String> tables){
        for (String table:tables){
            $(byText(table)).should(Condition.appear);
        }
    }

    @И("кнопками:$")
    public void checkOnLoadWindowWithButtons(List<String> buttons){
        for (String button:buttons){
            $(getElement(byValue(button)).should(Condition.appear));
        }
    }

    @Когда("^я введу в поле '(.+)' '(.+)'")
    public void inputUsers(String fieldName1, String fieldVal1){
        findFieldByUpperLabelAndSetValue(fieldName1, fieldVal1);
    }

    private void findFieldByUpperLabelAndSetValue(String fieldName1, String fieldVal1) {
        SelenideElement elem =  $(byText("Имя"));
        String idFor = elem.attr("for");
        SelenideElement elemForInput = $(byId(idFor));

        if (!elem.toString().contains("NoSuchElementException")){
            elemForInput.val("Александр").pressEnter();
        }else{
            elem.parent().parent().find(By.tagName("input")).val(fieldVal1).pressEnter();
        }

        /*SelenideElement elem = $(By.tagName("label")).find(byText(fieldName1));
        String idFor1 =  elem.parent().attr("for");

        SelenideElement elem1 = $(byId(idFor1));
        System.out.println("elem1 = " + elem1.toString());
        if (!elem1.toString().contains("NoSuchElementException")){
            elem1.val(fieldVal1).pressEnter();
        }else{
            elem.parent().parent().find(By.tagName("input")).val(fieldVal1).pressEnter();
        }*/
    }

    @И("^нажму кнопку '(.+)'")
    public void clickBtn2(String btnCaption){
        vaadinButtonClick(btnCaption);
    }

    private void vaadinButtonClick(String btnCaption) {
        $(byText(btnCaption)).parent().parent().click();
    }

    @То("^таблица будет содержать запись:$")
    public void checkOnRecordExist(List<String> records){
        checkOnLoad(records);
    }
}
