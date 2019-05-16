package com.gisainvestment.home;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import org.apache.commons.lang3.time.DateUtils;
import com.gisainvestment.DAO.AccountDAOimp;
import com.gisainvestment.DAO.ChoiceDAOimp;
import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.DAO.MenuDAOimp;
import com.gisainvestment.domain.Account;
import com.gisainvestment.domain.Menu;
import com.gisainvestment.domain.Choice;
import com.gisainvestment.domain.Investment;
import com.gisainvestment.utility.Locator;
import com.gisainvestment.domain.CommandPaymentGW;
import com.gisainvestment.DAO.PaymentClient;
import com.gisainvestment.DAO.SubscriberDAO;
import com.gisainvestment.config.Log4jConf;
import com.gisainvestment.domain.Subscriber;
import com.gisainvestment.utility.SimpleDateConverter;
import com.gisainvestment.utility.Validation;
import com.gisainvestment.wService.Sender;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class UssdFlow {

    static Logger LOG = Logger.getLogger(UssdFlow.class.getName());
    public static Integer EXIT = 98;
    public static Integer BACK = 99;
//    public static String BACKMSG = "Subira inyuma";
//    public static String EXITMSG = "Hagarika gusubiza";
//    public static Integer YES = 1;
//    public static Integer NO = 2;
    private Map<String, Object> session;
    private java.util.Stack selections;
    private Object input;
    private List choices;
    private String message;
    private String question;
    private String phoneNumber;
    private String sessionId;
    private Date startTime;
    private Date endTime;
    private MenuDAOimp menudao;
    private List choi;
    private Menu menu;
    private Menu parent;
    private String FREEFLOW;//from oltranz
    private String NEWREQUEST;//from oltranz

    static {
        new Log4jConf().loadLog4j();
    }

    public UssdFlow(String phone, String sessionId) {
        this.FREEFLOW = "C";
        this.parent = new Menu();
        this.menu = new Menu();
        this.menudao = new MenuDAOimp();
        this.session = new HashMap<>();
        this.phoneNumber = phone;
        this.sessionId = sessionId;
        this.selections = new Stack<>();
        this.startTime = SimpleDateConverter.toLocalTZReturnDate(new Date());
        start();
    }

    public UssdFlow() {
        this.FREEFLOW = "C";
        this.parent = new Menu();
        this.menu = new Menu();
        this.menudao = new MenuDAOimp();
        this.session = new HashMap<>();
        this.NEWREQUEST = "1";
    }

    private void start() {
        try {

            Account account = AccountDAOimp.getAccount(this.phoneNumber);
            Long testNumber = AccountDAOimp.countAccount();
            LOG.error(account);
            if (account != null) {
                ChoiceDAOimp choidaoimp = new ChoiceDAOimp();
//                parent = menudao.getMenuByCode("M10");
                menu = menudao.getMenuByCode("M102");
                this.choi = choidaoimp.getChoicesByMenu(menu);

//                List a = new ArrayList();
//                a.add(choi.get(0));
                this.selections.push(menu);//1
                this.message = null;
                this.choices = null;
                this.question = "Murakaza neza muri GISA Investment Ltd^" + menu.getName();
                session.put("Choices", choices);
                session.put("menuInSession", menu);
            } else {
                if (testNumber == 299) {
                    Subscriber subscriber = new Subscriber();
                    subscriber.setId(SubscriberDAO.countSubscriber());
                    subscriber.setTelephone(this.phoneNumber);
                    subscriber.setStatus("subscribed");
                    subscriber.setSubsciption_date(SimpleDateConverter.toLocalTZReturnDate(new Date()));
                    SubscriberDAO.create(subscriber);
                    String msg = "Mukiriya mwiza, umubare w'abantu twahariye igihe cy'igerageza wuzuye. Nomero yawe igiye k'urutonde rw'abantu bazamenyeshwa nidutangira k'umugaragaro.";
                    Sender.sendRouteSMS(this.phoneNumber, new Sender(msg));
                    LOG.error("Test Number Exceeded");
                    exit();
                }
                MenuDAOimp rootM = new MenuDAOimp();
                menu = rootM.getRootMenu();
                LOG.error(menu);
                ChoiceDAOimp choidaoimp = new ChoiceDAOimp();
                this.choi = choidaoimp.getChoicesByMenu(menu);
                this.selections.push(menu);//1
                this.message = null;
                this.choices = choi.subList(0, 1);
                this.question = menu.getName();
                session.put("Choices", choices);
                session.put("menuInSession", menu);
            }
            LOG.error("Menu in session" + menu);
            LOG.error("choices in session" + choices);

        } catch (Exception e) {
            LOG.error(e.toString());
            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
            this.question = null;
            this.choices = null;
            this.FREEFLOW = "B";
            exit();
        }
    }

    public void setInput(Object input) {
        this.input = input;
        this.process();
    }

    private void process() {
        if (isInputValid()) {

            Integer index = null;

            if (Objects.equals(index, EXIT)) {
                //

            } else if (Objects.equals(index, BACK)) {
                //

            } else {
                menu = (Menu) session.get("menuInSession");
                switch (menu.getCode()) {
//                switch ("M101111") {
                    //===============================================================

                    case "M10":
                        try {
                            if (input.toString().equals("1")) {
                                parent = menudao.getMenuByCode("M101");
                                if (parent.getCode().equals("M101")) {
                                    this.question = parent.getName();
                                    this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                    this.choices = this.choi;
                                    this.message = null;
                                    session.put("Choices", choi);
                                    session.put("menuInSession", parent);
                                }
                            } else {
                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                            }
                        } catch (Exception e) {
                            LOG.error("Error M10 " + e);
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                            this.message = "^Dear Esteemed customer, you have entered an invalid input,^ please try again.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }

                        break;
                    //===============================================================

                    case "M101":
                        try {
//                            index = Integer.parseInt(input.toString());

                            if (this.input.equals("1")) {
                                parent = menudao.getMenuByCode("M104");
                            } else if (this.input.equals("2")) {
                                parent = menudao.getMenuByCode("M1012");
                            }
//                                parent = menudao.getMenuByCode("M101" + index);
                            if (parent.getCode().equalsIgnoreCase("M104")) {
                                this.question = parent.getName();
                                this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
//                                this.choices = this.choi;
                                this.choices = null;
                                this.message = null;
                                session.put("Choices", choi);
                                session.put("menuInSession", parent);
                            } else if (parent.getCode().equalsIgnoreCase("M1012")) {
                                this.question = null;
                                this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                this.choices = null;
                                this.FREEFLOW = "B";
//                                this.message = "^Thanks you for trying GISA Investment.^ We hope to see you again.";
                                this.message = "^Murakoze guhitamo GISA INVESTMENT^, turakurarikira kuzagaruka gukoresha serivise zacu";
                                session.put("Choices", choi);
                                session.put("menuInSession", parent);
                            }

                        } catch (Exception e) {
                            LOG.error("Error M101-1" + e);
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }
                        break;
                    //===============================================================
                    case "M104"://IDENTIFICATION

                        try {
                            if (this.input.toString().isEmpty()) {
                                LOG.error("ERROR M1011: INPUT EMPTY");
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                                break;
                            } else if (this.input.toString().length() < 6) {
                                LOG.error("ERROR M1011: length");
                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                                break;
                            } else {

                                if (this.input.toString().length() == 16) {
                                    char identity = this.input.toString().charAt(5);//1199480021901026
                                    if (identity == '8' || identity == '7') {
                                        parent = menudao.getMenuByCode("M1011");
                                        this.question = parent.getName();
                                        this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                        this.choices = null;
                                        this.message = "Umubare w'inyuguti ntugomba kujya munsi ya 6^";
//                                        this.message = "^Caracters must not be less than 6^";
                                        session.put("Choices", choi);
                                        session.put("menuInSession", parent);
                                        session.put("identity", this.input.toString());
                                        selections.push(this.input.toString());//2
                                    }
                                }
                            }
                        } catch (Exception e) {
                            LOG.error("ERROR M1011: " + e.toString());
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                            this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }
                        break;

                    //===============================================================
                    case "M1011"://create password

                        try {
                            if (this.input.toString().isEmpty()) {
                                LOG.error("ERROR M1011: INPUT EMPTY");
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                                break;
                            } else if (this.input.toString().length() < 6) {
                                LOG.error("ERROR M1011: length");
                                this.message = "^Mukiriya mwiza, Umubare w'inyuguti ntugomba kujya munsi ya 6, mwongere mukanya";
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                                break;
                            } else {
                                parent = menudao.getMenuByCode("M10111");
                                this.question = parent.getName();
                                this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                this.choices = null;
//                                this.choices = this.choi;
                                this.message = null;

                                session.put("Choices", choi);
                                session.put("menuInSession", parent);
                                session.put("passwd", this.input.toString());

                            }
                        } catch (Exception e) {
                            LOG.error("ERROR M1011: " + e.toString());
//                            this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }
                        break;

                    //===============================================================
                    case "M10111"://confirm password

                        try {
                            String previousinput = (String) session.get("passwd");
                            if (this.input.toString().equals(previousinput)) {
                                selections.push(this.input.toString());//3
                                if (endRegistration()) {
                                    parent = menudao.getMenuByCode("M101111");
                                    this.question = parent.getName();
                                    this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                    this.choices = this.choi;
                                    this.message = null;
                                    session.put("Choices", choi);
                                    session.put("menuInSession", parent);

                                } else {
                                    LOG.error("ERROR M10111: ");
                                    this.message = "^Mukiriya mwiza, ubufasha musabye ntibubashije kuboneka,^ mwongere mukanya.";
//                                    this.message = "^Dear Esteemed customer, the System countered an error, ^please try again later";
                                    this.question = null;
                                    this.choices = null;
                                    this.FREEFLOW = "B";
                                    exit();
                                }
                            } else {
//                                this.message = "^Dear customer, password doesn't match, ^please try again.";
                                this.message = "^Mukiriya mwiza, ijambo ry'ibanga ntabwo ariryo,^ mwongera mugerageze.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                            }
                        } catch (Exception e) {
                            LOG.error("ERROR M10111: " + e.toString());
                            this.message = "^Mukiriya mwiza, umubare mushizemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }
                        break;

                    //===============================================================
                    case "M101111"://Start investing
                        try {
                            parent = menudao.getMenuByCode("M10211");// choose platform
                            this.question = parent.getName();
                            this.choi = ChoiceDAOimp.getChoicesByMenu(parent, this.phoneNumber);
                            this.choices = this.choi;
                            this.message = null;
                            session.put("Choices", choi);
                            session.put("menuInSession", parent);

                        } catch (Exception e) {
                            LOG.error("ERROR M101111: " + e.toString());
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                            this.message = "^Dear customer, you have entered an invalid input, ^please try again.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }

                        break;

                    //===============================================================
                    case "M102"://enter password
                        try {
                            Account account = AccountDAOimp.getAccount(new Account(this.phoneNumber, this.input.toString()));
                            if (account != null) {
                                parent = menudao.getMenuByCode("M1021");
                                this.question = parent.getName();
                                this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                this.choices = this.choi;
                                this.message = null;
                                selections.push(account);//4
                                session.put("Choices", choi);
                                session.put("menuInSession", parent);
                            } else {
                                parent = menudao.getMenuByCode("M102");
                                this.question = "ijambo ry'ibanga ushizemo ntabwo ari ryo^" + parent.getName();
                                this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                this.choices = null;
                                this.message = null;
                                session.put("Choices", choi);
                                session.put("menuInSession", parent);
                            }

                        } catch (Exception e) {
                            LOG.error("ERROR M102: " + e.toString());
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                            this.message = "^Error: M102 Dear Esteemed customer, you have entered an invalid input,^ please try again.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }
                        break;

                    //===============================================================
                    case "M1021"://investment menu
                        try {
                            index = Integer.parseInt(input.toString());
                            if (index instanceof Integer) {
                                index = Integer.parseInt(input.toString());
                                parent = menudao.getMenuByCode("M1021" + index);
                                LOG.error("TRACK INPUT investment menu" + index);
                                LOG.error("TRACK INPUT investment menu" + input);
                                switch (parent.getCode()) {
                                    case "M10211":
                                        this.question = parent.getName();
                                        this.choi = ChoiceDAOimp.getChoicesByMenu(parent, this.phoneNumber);
                                        this.choices = this.choi;
                                        this.message = null;
                                        session.put("Choices", choi);
                                        session.put("menuInSession", parent);
                                        break;
                                    case "M10212"://mini-statement

                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY HH:mm");
                                        Account account = (Account) selections.get(selections.size() - 1);
                                        List<Investment> investmentByReference = InvestmentDAOimp.miniStatement(account.getReference());

                                        List<String> msg = new ArrayList<>();
                                        for (Investment inv : investmentByReference) {
                                            msg.add(inv.getAmount() + "Frw On " + sdf.format(inv.getInv_date()) + "^" + inv.getReceivableAmount() + "Frw On " + sdf.format(inv.getInv_returnDate()) + "^");

                                        }

                                        LOG.error("Mini-statement " + msg.toString().substring(1, msg.toString().length()));
                                        this.message = msg.toString().substring(1, msg.toString().length() - 1);
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        exit();

                                        break;
                                    case "M10213"://guhindura ijambo ry'ibanga
                                        this.question = parent.getName();
                                        this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                        this.choices = this.choi;
                                        this.message = null;
                                        session.put("Choices", choi);
                                        session.put("menuInSession", parent);
                                        break;
                                    default:
                                        LOG.error("ERROR M1021: default case");
                                        this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        break;
                                }
                            } else {
                                LOG.error("ERROR M1021: ");
                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input,^ please try again.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                            }
                        } catch (Exception e) {
                            LOG.error("Exception ERROR M1021: " + e.toString());
//                            this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            exit();
                        }
                        break;

                    //===============================================================
                    case "M10211"://Choose platform MENU 

                        try {
                            index = Integer.parseInt(input.toString());
                            if (index instanceof Integer) {
                                index = Integer.parseInt(input.toString());
                                switch (index) {
                                    case 1:
                                        parent = menudao.getMenuByCode("M10211" + index);// Get this Menu M102111 :Enter amount menu
                                        this.question = parent.getName();
                                        this.choi = ChoiceDAOimp.getChoicesByMenu(parent, this.phoneNumber);
//                                        this.choices = this.choi;
                                        this.choices = null;
                                        this.message = null;
                                        session.put("Choices", choi);
                                        session.put("menuInSession", parent);
                                        break;
                                    case 2:
                                        this.message = "Mukiriya mwiza, umuyoboro uhisemo nturatangira gukoreshwa. Hitamo undi muyoboro";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        Subscriber subscriber = new Subscriber();
                                        subscriber.setId(SubscriberDAO.countSubscriber());
                                        subscriber.setTelephone(this.phoneNumber);
                                        subscriber.setStatus("subscribed");
                                        subscriber.setSubsciption_date(SimpleDateConverter.toLocalTZReturnDate(new Date()));
                                        SubscriberDAO.create(subscriber);
                                        exit();
                                        break;
                                    case 3:
                                        this.message = "Mukiriya mwiza, umuyoboro uhisemo nturatangira gukoreshwa. Hitamo undi muyoboro";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        Subscriber subscriber2 = new Subscriber();
                                        subscriber2.setId(SubscriberDAO.countSubscriber());
                                        subscriber2.setTelephone(this.phoneNumber);
                                        subscriber2.setStatus("subscribed");
                                        subscriber2.setSubsciption_date(SimpleDateConverter.toLocalTZReturnDate(new Date()));
                                        SubscriberDAO.create(subscriber2);
                                        exit();
                                        break;
                                    default:
                                        this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        exit();
                                        break;
                                }
                            } else {
                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                            }
                        } catch (Exception e) {
//                            this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            LOG.error(e.toString());
                            exit();
                        }
                        break;

                    //===============================================================
                    case "M102111": //Enter MTN Amount
                        try {
                            Account account = (Account) selections.get(selections.size() - 1);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String formatedDate = sdf.format(SimpleDateConverter.toLocalTZReturnDate(new Date()));
                            List<Investment> invest = InvestmentDAOimp.getInvestmentByReferenceForToday(account.getReference(), sdf.parse(formatedDate));
                            int size = invest.size();
                            if (size <= 20) {
                                if (!this.input.toString().isEmpty()) {
                                    System.out.println(this.input.toString());
                                    double inputint = Double.parseDouble(this.input.toString());

                                    if (isInvestmentAmountValid(inputint, this.phoneNumber)) {
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        String transactionID = Investment.generateTicketNumber();
                                        CommandPaymentGW command = new CommandPaymentGW();

                                        command.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                                        command.setPAYINGACCOUNTIDATSP(this.phoneNumber);
                                        if (this.phoneNumber.startsWith("25078")) {
                                            command.setPAYMENTSPID(Locator.WS_MTN_PAYMENTSPID);
                                        } else if (this.phoneNumber.startsWith("25073")) {
                                            command.setPAYMENTSPID(Locator.WS_AIRTEL_PAYMENTSPID);
                                        } else if (this.phoneNumber.startsWith("25072")) {
                                            command.setPAYMENTSPID(Locator.WS_TIGO_PAYMENTSPID);
                                        }
                                        command.setDESCR("MURAKOZE KUGURIZA GISA INVESTMENT Ltd");
//                                    command.setDESCR("THANKS FOR INVESTING IN GISA INVESTMENT Ltd");
                                        command.setTRANSID(transactionID);
                                        command.setAMOUNT(inputint);
//                                    selections.push(transactionID);//5

                                        if (endInvestmentTelcom(inputint, transactionID)) {
                                            size = size + 1;
                                            this.message = "^Mukiriya mwiza, mutegereze ubutumwa bugufi bwemeza igikorwa mukoze. ^Musigaje ishuro " + (20 - size) + " zo kongera kuguriza";
                                            PaymentClient paymentClient = new PaymentClient(command);
                                            paymentClient.initiatePaymentRequestTread(command);
                                            exit();
                                        } else {
                                            this.message = "^Mukiriya mwiza,igikorwa mukoze ntigikunze,^ mwongere mukanya.";
//                                        this.message = "^Dear Esteemed customer, the System countered an error, ^please try again later";
                                            LOG.error("ERROR M102111 -01");
                                            exit();
                                        }
                                        exit();

                                    } else {
                                        if (this.phoneNumber.startsWith("25078")) {
                                            this.message = "^Mukiriya mwiza, Mwemerewe kohereza amafaranga ari hagari ya RWF 10,000 - 50,000, ^Mwongere mukanya.";
                                        } else if (this.phoneNumber.startsWith("25073")) {
                                            this.message = "^Mukiriya mwiza, Mwemerewe kohereza amafaranga ari hagari ya RWF 1,000 - 50,000, ^Mwongere mukanya.";
                                        } else if (this.phoneNumber.startsWith("25072")) {
                                            this.message = "^Mukiriya mwiza, Mwemerewe kohereza amafaranga ari hagari ya RWF 10,000 - 50,000, ^Mwongere mukanya.";
                                        }
//                                    this.message = "^Dear Esteemed customer, you have not allowed to invest more than 50,000 Frw, ^please try less.";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        LOG.error("ERROR M102111 -1");
                                        exit();
                                    }
                                    exit();
                                } else {
                                    this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                                    this.question = null;
                                    this.choices = null;
                                    this.FREEFLOW = "B";
                                    LOG.error("ERROR M102111 -2");
                                    exit();
                                }

                            } else {
                                this.message = "^Mukiriya mwiza, mwarengeje inshuro " + size + " zo kuguriza GISA Investement Ltd kumunsi^ muzongera ejo.";
//                                this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                LOG.error("ERROR M102111 -2");
                                exit();
                            }
                            exit();

                        } catch (Exception e) {
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                            this.message = "^Dear Esteemed customer, you have entered an invalid input, ^please try again.";
                            this.question = null;
                            this.choices = null;
                            this.FREEFLOW = "B";
                            LOG.error("ERROR M102111 -3 " + e.toString());
                            exit();
                        }
                        break;
                    //===============================================================

                    case "M10213":// change password menu
                        try {
                            if (this.input.toString().length() < 6) {
                                LOG.error("ERROR M1011: length");
                                this.message = "^Mukiriya mwiza, Umubare w'inyuguti ntugomba kujya munsi ya 6, mwongere mukanya";
                                this.question = null;
                                this.choices = null;
                                this.FREEFLOW = "B";
                                exit();
                            } else {
                                session.put("newPassword", this.input.toString());
                                parent = menudao.getMenuByCode("M102131");
                                this.question = parent.getName();
                                this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
                                this.choices = this.choi;
                                this.message = null;
                                session.put("Choices", choi);
                                session.put("menuInSession", parent);
                            }
                        } catch (Exception e) {
                            LOG.error("M10213 " + e.toString());
                            this.question = null;
                            this.choices = null;
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.FREEFLOW = "B";
                            exit();
                            break;
                        }

                        break;
                    //===============================================================

                    case "M102131":// change password menu (confirm)
                        try {
                            index = Integer.parseInt(input.toString());
                            switch (index) {
                                case 1:
                                    String newPassword = (String) session.get("newPassword");
                                    Account account = (Account) selections.get(selections.size() - 1);
                                    account.setPassword(newPassword);
                                    if (AccountDAOimp.resetPassword(account)) {
                                        this.message = "Ijambo ry'ibanga ryahindutse, Ubutaha winjire n'ijambo ry'ibanga rishyashya";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        exit();
                                    } else {
                                        this.message = "Ijambo ry'ibanga ryahindutse, Ubutaha winjire n'ijambo ry'ibanga rishyashya";
                                        this.question = null;
                                        this.choices = null;
                                        this.FREEFLOW = "B";
                                        exit();
                                    }
                                    break;
                                case 2:
                                    this.question = null;
                                    this.choices = null;
                                    this.message = "Ijambo ry'ibanga ntiryahindutse, ukomeze winjire n'ijambo ry'ibanga usanganwe";
                                    this.FREEFLOW = "B";
                                    exit();
                                    break;
                                default:
                                    this.question = null;
                                    this.choices = null;
                                    this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                                    this.FREEFLOW = "B";
                                    exit();
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            LOG.error("M10213 " + e.toString());
                            this.question = null;
                            this.choices = null;
                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
                            this.FREEFLOW = "B";
                            exit();
                            break;
                        }

                        break;
                    //===============================================================
//                    case "M1022":
//                        try {
//                            index = Integer.parseInt(input.toString());
//                            if (index instanceof Integer) {
//                                index = Integer.parseInt(input.toString());
//                                if (index == 1) {
//                                    parent = menudao.getMenuByCode("M102");
//                                    this.question = parent.getName();
//                                    this.choi = new ChoiceDAOimp().getChoicesByMenu(parent);
//                                    this.choices = this.choi;
//                                    this.message = null;
//                                    session.put("Choices", choi);
//                                    session.put("menuInSession", parent);
//                                }
//                            } else {
//                                this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                                this.question = null;
//                                this.choices = null;
//                                this.FREEFLOW = "B";
//                                exit();
//                            }
//                        } catch (Exception e) {
//                            this.message = "^Mukiriya mwiza, umubare muhisemo ntabwo ariwo,^ mwongera mugerageze.";
//                            this.question = null;
//                            this.choices = null;
//                            this.FREEFLOW = "B";
//                            LOG.error(e.toString());
//                            exit();
//                        }
//                        break;
                    //===============================================================
                    default:
                        this.message = "^Serivise musabye ntibashije kuboneka, bitewe n'ikibazo cya tekinike."
                                + "Murakoze!";
                        this.question = null;
                        this.choices = null;
                        this.FREEFLOW = "B";
                        exit();
                    //===============================================================
                }//end switch
            }//end not exit not back

        } else {
            this.message = "^Dear Esteemed customer, you have entered an invalid input, please try again.";
        }
    }

    public boolean isInputValid() {
        boolean valid = true;
        /* Start validation input */

 /* End validation input */
        return valid;
    }

    public boolean isInvestmentAmountValid(double amount, String phone) {
        /* Start validation input */
        boolean valid = false;
        if (phone.startsWith("25078")) {
            if (amount >= 10000 && amount <= 50000) {
                valid = true;
            }
        } else if (phone.startsWith("25073")) {
            if (amount >= 1000 && amount <= 50000) {
                valid = true;
            }
        } else if (phone.startsWith("25072")) {
            if (amount >= 10000 && amount <= 50000) {
                valid = true;
            }
        }
        return valid;
        /* End validation input */
    }

    public void process(Integer input) {
        this.input = input;

    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Object getInput() {
        return input;
    }

    public Stack getSelections() {
        return selections;
    }

    public String getMessage() {
        return message;
    }

    public String getQuestion() {
        return question;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    //============================================
    public List getChoices1() {
        return choices;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public MenuDAOimp getMenudao() {
        return menudao;
    }

    public void setMenudao(MenuDAOimp menudao) {
        this.menudao = menudao;
    }

    public List<Choice> getChoi() {
        return choi;
    }

    public void setChoi(List<Choice> choi) {
        this.choi = choi;
    }

    public String getFREEFLOW() {
        return FREEFLOW;
    }

    public void setFREEFLOW(String FREEFLOW) {
        this.FREEFLOW = FREEFLOW;
    }

    public String getNEWREQUEST() {
        return NEWREQUEST;
    }

    public void setNEWREQUEST(String NEWREQUEST) {
        this.NEWREQUEST = NEWREQUEST;
    }

//======================================================
    public boolean endRegistration() {
        try {
            endTime = SimpleDateConverter.toLocalTZReturnDate(new Date());
            Account account = new Account(
                    AccountDAOimp.countAccount() + 1,
                    Account.generateReferenceNumber(),
                    phoneNumber,
                    selections.get(selections.size() - 1).toString(),//pass
                    selections.get(selections.size() - 2).toString(),//ID Number
                    startTime,
                    endTime,
                    "Active"
            );
            selections.push(account);
            return AccountDAOimp.create(account);
        } catch (Exception e) {
            LOG.error("Exception " + e.toString());
            return false;
        }
    }

    public boolean endInvestmentTelcom(double amount, String transactionID) {

        try {
            Account account = (Account) selections.get(selections.size() - 1);

            Date invdate = SimpleDateConverter.toLocalTZReturnDate(new Date());
            double balance = 0;
            for (Investment inv : InvestmentDAOimp.getInvestmentByReference(account.getReference())) {
                if (null == inv) {
                    balance = 0;
                } else {
                    balance = inv.getBalance();
                }
            }

            double investedAmount = amount;
            double amount_in_sys = Validation.formatDouble(investedAmount * Locator.GISA_SYSTEM_IN);
            double amount_in_circulation_store = Validation.formatDouble(amount_in_sys * Locator.GISA_SYSTEM_CILCULATION_STORE);
            double amount_in_investment_world = Validation.formatDouble(amount_in_sys * Locator.GISA_SYSTEM_INVESTMENT_WORLD);
            double receivableAmount = 0;
            if (account.getPhone().startsWith("25078")) {
                receivableAmount = amount + (amount * Locator.GISA_INTEREST_MTN);
            } else if (account.getPhone().startsWith("25073")) {
                receivableAmount = amount + (amount * Locator.GISA_INTEREST_AIRTEL);
            } else if (account.getPhone().startsWith("25072")) {
                receivableAmount = amount + (amount * Locator.GISA_INTEREST_TIGO);
            }

            Investment investment;
            investment = new Investment(
                    InvestmentDAOimp.countInvestment() + 1,
                    account.getReference(),
                    transactionID,
                    invdate,
                    investedAmount,
                    SimpleDateConverter.addDays(invdate, 30),
                    receivableAmount,
                    balance + receivableAmount,
                    this.phoneNumber,//Phone-Source
                    "Pending",
                    "Queued",
                    amount_in_sys,
                    amount_in_circulation_store,
                    amount_in_investment_world
            );
            LOG.error(investment);
            return InvestmentDAOimp.create(investment);
        } catch (Exception e) {
            LOG.error("Exception " + e.toString());
            return false;
        }
    }

    /**
     * Treat the response from mobile money payment gateway to validate the
     * transfer of money to GISA ACCOUNT
     * ------------------------------------------------------ validateTicket()
     * receive payment gateway response as parameter check if this response
     * don't equal to null retrieve the investment object has the transaction
     * ticket in the response from gateway check if the investment retrieved
     * don't equal to null check the response status if is 100 (success
     * transfer) Format the date to dd-mm-yyy and update transactionStatus to
     * success update the Investment Object Send back the success response to
     * Payment gateway if the investment object updated else send failure
     * response to the payment gateway.
     */
    public static CommandPaymentGW validateTicket(CommandPaymentGW resp) {
        LOG.error("====VALIDATETICKET() incomming response \n" + resp);
        CommandPaymentGW response = new CommandPaymentGW();
        try {
            if (!Validation.isObjectNull(resp.getTRANSID())) {
                Investment inv = InvestmentDAOimp.checkTicket(resp.getTRANSID());
                LOG.error("From InvestmentDAOimp.checkTicket()" + inv);
                if (!Validation.isObjectNull(inv)) {
                    if (("100").equals(resp.getSTATUSCODE())) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String inv_date = sdf.format(inv.getInv_date());
                            String inv_return_date = sdf.format(inv.getInv_returnDate());
                            inv.setTransactionStatus("success");
                            if (InvestmentDAOimp.updateTicket(inv)) {
                                Sender s1 = new Sender("Mukiriya mwiza, Kuwa " + inv_date + " ugurije GISA INVESTMENT Ltd " + inv.getAmount() + "Rwf , uzishyurwa " + inv.getReceivableAmount() + "Rwf bitarenze " + inv_return_date + ". Itike yawe ni:" + inv.getTicket() + ".");
                                Sender.sendRouteSMS(inv.getSource(), s1);
                                response.setTRANSID(resp.getTRANSID());
                                response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                                response.setSTATUSCODE("100");
                                response.setDESCR("Success");
                                return response;
                            } else {
                                //when the API credited successfully the client, but our app fail to update the transaction in database
                                Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd ibabajwe no kubamenyesha ko igikorwa mukoze kitakunze, mwongere mukanya");
                                Sender.sendRouteSMS(inv.getSource(), s);
                                inv.setTransactionStatus("fail");
                                InvestmentDAOimp.updateTicket(inv);
                                response.setTRANSID(resp.getTRANSID());
                                response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                                response.setSTATUSCODE("101");//code to notify the failure
                                response.setDESCR("The system fail to update this transaction");
                                return response;
                            }
                        } catch (Exception e) {
                            LOG.error("====EXCEPTION IN IF (100) " + e.toString());
                            Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd ibabajwe no kubamenyesha ko igikorwa mukoze kitakunze, mwongere mukanya");
                            Sender.sendRouteSMS(inv.getSource(), s);
                            inv.setTransactionStatus("fail");
                            InvestmentDAOimp.updateTicket(inv);
                            response.setTRANSID(resp.getTRANSID());
                            response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                            response.setSTATUSCODE("101");//code to notify the failure
                            response.setDESCR("The system fail to update this transaction");
                            return response;
                        }

                    } else if (("206").equals(resp.getRESPONDERSTATUS()) || ("206").equals(resp.getRESPONDERSTATUS()) || ("306").equals(resp.getREQUESTSTATUS())) {
                        LOG.error("====RESPONDERSTATUS 206,206 and REQUESTSTATUS 306 response \n" + resp);
                        Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd irabamenyesha ko igikorwa mukoze kitakunze, mwongere mukanya");
                        Sender.sendRouteSMS(inv.getSource(), s);
                        inv.setTransactionStatus("fail");
                        InvestmentDAOimp.updateTicket(inv);
                        response.setTRANSID(resp.getTRANSID());
                        response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                        response.setSTATUSCODE("101");//code to notify the failure
                        response.setDESCR("Failure response");
                        return response;
                    } else {
                        LOG.error("====VALIDATETICKET() Unknown response \n" + resp);
                        Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd irabamenyesha ko igikorwa mukoze kitakunze, mwongere mukanya");
                        Sender.sendRouteSMS(inv.getSource(), s);
                        inv.setTransactionStatus("fail");
                        InvestmentDAOimp.updateTicket(inv);
                        response.setTRANSID(resp.getTRANSID());
                        response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                        response.setSTATUSCODE("101");//code to notify the failure
                        response.setDESCR("Unknown response");
                        return response;
                    }

                } else {
                    LOG.error("====VALIDATETICKET() Unknown TRANSACTION ID: \n" + resp);
                    Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd irabamenyesha ko igikorwa mukoze kitakunze, mwongere mukanya");
                    Sender.sendRouteSMS(inv.getSource(), s);
                    inv.setTransactionStatus("fail");
                    response.setTRANSID(resp.getTRANSID());
                    response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                    response.setSTATUSCODE("101");//code to notify the failure
                    response.setDESCR("Unknown TRANSACTION ID");
                    return response;
                }
            } else {
                LOG.error("====VALIDATETICKET() NULL INCOMMING ARGUMENT");
                response.setTRANSID(resp.getTRANSID());
                response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
                response.setSTATUSCODE("101");//code to notify the failure
                response.setDESCR("EXCEPTION ERROR NULL");
                return response;
            }
        } catch (Exception e) {
            LOG.error("====Exception: " + e.toString());
            response.setTRANSID(resp.getTRANSID());
            response.setCONTRACTID(Locator.WS_OLTRANZ_API_CONTRACTID);
            response.setSTATUSCODE("101");//code to notify the failure
            response.setDESCR("EXCEPTION ERROR");
            return response;
        }
    }

    public void exit() {
        UssdSession.removeFlow(this);
    }

//    //SEND SMS TO CLIENT
//    private static void sendConfirmationMessage(CommandPaymentGW command) {
//        SmsMessage response = new SmsMessage();
//        response.setDest(command.getPAYINGACCOUNTIDATSP());
//        response.setMessage("Dear esteemed customer, you have invested "
//                + command.getAMOUNT() + "Rwf "
//                + "in GISA INVESTMENT Ltd, The TRANSACTION ID is: "
//                + command.getTRANSID() + ".");
//        new SmsService().sendsms(response);
//    }
    //SEND SMS TO CLIENT routesms
//    private static void sendConfirmationMessageToRouteSMS(CommandPaymentGW command) {
//        SmsMessage response = new SmsMessage();
//
//        response.setDest(command.getPAYINGACCOUNTIDATSP());
//        response.setMessage("Dear esteemed customer, you have invested "
//                + command.getAMOUNT() + "Rwf "
//                + "in GISA INVESTMENT Ltd, The TRANSACTION ID is: "
//                + command.getTRANSID() + ".");
//        new SmsService().sendsms(response);
//
//        
//    }
}
