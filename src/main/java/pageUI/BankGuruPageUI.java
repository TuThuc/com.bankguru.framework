package pageUI;

public class BankGuruPageUI {
    public class RegisterPageUI {
        public static final String EMAIL_ADDRESS_TEXTBOX = "name=emailid";
        public static final String SUBMIT_BUTTON = "name=btnLogin";
        public static final String USERID_INFOR = "xpath=//td[text()='User ID :']/following-sibling::td";
        public static final String PASSWORD_INFOR ="xpath=//td[text()='Password :']/following-sibling::td";

    }
    public static class LoginPageUI {
        public static final String LOGIN_BUTTON = "name=btnLogin";
        public static final String USERID_TEXTBOX = "name=uid";
        public static final String PASSWORD_TEXTBOX ="name=password";
        public static final String HERE_LINK ="xpath=//a[text()='here']";
    }
    public static class HomePageUI {
        public static final String LOGIN_SUCCESS_MESSAGE = "xpath=//marquee[contains(text(),'Guru99 Bank')]";
        public static final String TITLE_PAGE_SUCCESS_MESSAGE = "xpath=//p[@class='heading3']";
        public static final String DYNAMIC_VALUE_TEXT_BY_TITLE = "xpath=//td[text()='%s']/following-sibling::td";
    }

        public static class AmountPageUI{
        public static final String ACCOUNT_NO_TEXTBOX = "name=accountno";

        public static final String AMOUNT_TEXTBOX = "name=ammount";
        public static final String DESCRIPTION_TEXTBOX = "name=desc";
        public static final String SUBMIT_BUTTON = "name=AccSubmit";

    }
    public static class WithdrawalPageUI{
        public static final String ACCOUNT_NO_TEXTBOX = "name=accountno";

        public static final String AMOUNT_TEXTBOX = "name=ammount";
        public static final String DESCRIPTION_TEXTBOX = "name=desc";
        public static final String SUBMIT_BUTTON = "name=AccSubmit";

    }
    public static class NewCustomerPageUI{
        public static final String ADDRESS_TEXTAREA = "name=addr";
    }
    public  static class EditCustomerPageUI{
        public static  final String EDIT_CUSTOMER_PAGE_TITLE = "xpath=//p[@class='heading3']";
        public static final String ADDRESS_TEXTAREA = "name=addr";
    }
    public static class DeleteCustomerPageUI{
        public static final String CUSTOMER_ID_TEXTBOX = "name=cusid";
        public static final String SUBMIT_BUTTON = "name=AccSubmit";    }
    public static class NewAccountPageUI{
        public static final String ACCOUNT_TYPE_DROPDOWN = "name=selaccount";
    }
    public static class EditAccountPageUI{
        public static final String ACCOUNT_TYPE_DROPDOWN = "name=selaccount";
    }
    public static class DeleteAccountPageUI{
        public static final String ACCOUNT_NO_TEXTBOX = "name=accountno";
        public static final String SUBMIT_BUTTON = "name=AccSubmit";    }
    public static class FundTransferPageUI{
        public static final String PAYER_ACCOUNT_NO_TEXTBOX = "name=payersaccount";
        public static final String PAYEER_ACCOUNT_NO_TEXTBOX = "name=payeeaccount";
        public static final String AMOUNT_TEXTBOX = "name=ammount";
        public static final String DESCRIPTION_TEXTBOX = "name=desc";
        public static final String SUBMIT_BUTTON = "name=AccSubmit";

    }
    public static class BalanceEnquiryPageUI {
        public static final String ACCOUNT_NO_TEXTBOX = "name=accountno";
        public static final String SUBMIT_BUTTON = "name=AccSubmit";
    }
}
