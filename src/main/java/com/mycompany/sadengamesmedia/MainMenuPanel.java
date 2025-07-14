/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;
import com.mycompany.components.GradientPanel;
import static com.mycompany.components.GradientPanel.GradientType.*;
import com.mycompany.components.IconButton;
import com.mycompany.sadengamesmedia.model.ProductItem;
import com.mycompany.sadengamesmedia.model.Session;
import com.mycompany.sadengamesmedia.model.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author denia
 */
public class MainMenuPanel extends JPanel {
    private SadenGamesMedia sgm;
    
    public MainMenuPanel(){
    
    }
    
    public MainMenuPanel(SadenGamesMedia sgm) throws SQLException{
        this.sgm = sgm;
        accountButton = new IconButton("images/myAccountIcon.png", 100, 100);
        accountPanel = new AccountPanel(sgm);
        initComponents();
        loadProfileImage();
    }
    
        public void loadProfileImage() {
            User user = Session.getCurrentUser();
            System.out.println("inainte de test ");
            String path = Paths.get(System.getProperty("user.dir"), "profile_images", "myAccountIcon.png").toString();
            if (user != null) {
                if(!Session.getCurrentUser().getRole().toLowerCase().equals("admin")){
                    addItem.setVisible(false);
                    refresh.setVisible(false);
                    financeButton.setVisible(false);
                    usersButton.setVisible(false);
                    
                }else{
                    addItem.setVisible(true);
                    refresh.setVisible(true);
                    financeButton.setVisible(true);
                    usersButton.setVisible(true);
                }
                if(user.getImagePath() != null){
                    System.out.println("trebuie sa mearga imaginea");
                    accountButton.setNewIcon(user.getImagePath(), 100, 100);
                    accountPanel.loadImageStartUp(user.getImagePath(), 300, 300);
                    accountPanel.setTextFieldsValues();
                }else{
                    System.out.println("imaginea  e null");
                    accountPanel.setTextFieldsValues();
                }
            } else {
                System.out.println("userul  e null");
                accountButton.setIcon(path, 100, 100);
                accountPanel.loadImageStartUp(path, 300, 300);
            }

            revalidate();
            repaint();
        }

        
        public void onShow() {
            loadProfileImage();
        }
    
    
    private void initComponents() throws SQLException {
        setLayout(null);

        sideMenu.setBackground(Color.DARK_GRAY);
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBounds(0, 0, sideMenuWidth, getHeight());
        add(sideMenu);
        
        mainContent.setBounds(0, 50, 1200, 750);
        mainContent.setLayout(null);
        mainContent.setVisible(true);
        mainContent.setBackground(new Color(10,15,20));
        add(mainContent);
        
        topPanel.setBounds(0, 0, 1200, 50);
        topPanel.setLayout(null);
        topPanel.setVisible(true);
        add(topPanel);
        
        
        accountPanel.setBounds(0, 50, 1200, 750);
        accountPanel.setVisible(false);
        add(accountPanel);
        
        
        financePanel.setBounds(0, 50, 1200, 750);
        financePanel.setVisible(false);
        add(financePanel);
        
        
        usersPanel.setBounds(0, 50, 1200, 750);
        usersPanel.setVisible(false);
        add(usersPanel);
        
        
        sideMenuButton.setBounds(2, 0, 48, 48);
        sideMenuButton.setBorderPainted(false);
        sideMenuButton.setFocusPainted(false);
        sideMenuButton.setContentAreaFilled(false);
        topPanel.add(sideMenuButton);
        sideMenuButton.addActionListener(e -> sideMenuAnimation());
       
        
        accountButton.setPreferredSize(new Dimension(100, 100));
        accountButton.setMaximumSize(new Dimension(150, 100));
        accountButton.setVisible(true);
        accountButton.setOpaque(false);                     
        accountButton.setContentAreaFilled(false);           
        accountButton.setBorderPainted(false);
        accountButton.setFocusPainted(false); 
        accountButton.addMouseListener(accountButtonAction());
        sideMenu.add(accountButton);   
        
        sideMenu.add(Box.createVerticalStrut(30));
        
        productsButton.setPreferredSize(new Dimension(100, 50));
        productsButton.setMaximumSize(new Dimension(150, 50));
        productsButton.setVisible(true);
        productsButton.setFont(new Font("Courier New", Font.BOLD ,20));      
        productsButton.setForeground(Color.WHITE);
        productsButton.setOpaque(false);                     
        productsButton.setContentAreaFilled(false);           
        productsButton.setBorderPainted(false);
        productsButton.setFocusPainted(false);
        productsButton.addMouseListener(productsButtonAction());
        sideMenu.add(productsButton); 
        
        sideMenu.add(Box.createVerticalStrut(360));
        
        financeButton.setPreferredSize(new Dimension(100, 50));
        financeButton.setMaximumSize(new Dimension(150, 50));
        financeButton.setFont(new Font("Courier New", Font.BOLD ,20));      
        financeButton.setForeground(Color.WHITE);
        financeButton.setOpaque(false);                     
        financeButton.setContentAreaFilled(false);           
        financeButton.setBorderPainted(false);
        financeButton.setFocusPainted(false);
        financeButton.addMouseListener(financeButtonAction());
        sideMenu.add(financeButton);
        
        sideMenu.add(Box.createVerticalStrut(20));
        
        usersButton.setPreferredSize(new Dimension(100, 50));
        usersButton.setMaximumSize(new Dimension(150, 50));
        usersButton.setFont(new Font("Courier New", Font.BOLD ,20));      
        usersButton.setForeground(Color.WHITE);
        usersButton.setOpaque(false);                     
        usersButton.setContentAreaFilled(false);           
        usersButton.setBorderPainted(false);
        usersButton.setFocusPainted(false);
        usersButton.addMouseListener(usersButtonAction());
        sideMenu.add(usersButton);
        
        sideMenu.add(Box.createVerticalStrut(10));

        sideMenuCloseButton.setPreferredSize(new Dimension(100, 50));
        sideMenuCloseButton.setMaximumSize(new Dimension(150, 50));
        sideMenuCloseButton.setVisible(true);
        sideMenuCloseButton.setOpaque(false);                     
        sideMenuCloseButton.setContentAreaFilled(false);           
        sideMenuCloseButton.setBorderPainted(false);
        sideMenuCloseButton.setFocusPainted(false);
        sideMenu.add(sideMenuCloseButton);   
        sideMenuCloseButton.addActionListener(e -> sideMenuAnimation());
        
        allProducts = ProductItem.getAllProducts();
        productsPanel = new ProductsListPanel(allProducts);
        productsPanel.setBounds(200, 100, 970, 600);
        productsPanel.setVisible(true);
        mainContent.add(productsPanel);
        
        
        productTypeLabel.setBounds(920, 50, 100, 30);                     
        productTypeLabel.setVisible(true);
        productTypeLabel.setForeground(new Color(102, 100, 204));
        productTypeLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        mainContent.add(productTypeLabel);
        
        productType.setBounds(1000,50,150,30);
        productType.setVisible(true);
        productType.setFont(new Font("Arial", Font.BOLD ,15));
        productType.setBackground(new Color(30, 35, 60));
        productType.setForeground(Color.WHITE);
        productType.addActionListener(comboBoxesAction());
        mainContent.add(productType);
        
        
        sortByPriceLabel.setBounds(200, 50, 100, 30);                     
        sortByPriceLabel.setVisible(true);
        sortByPriceLabel.setForeground(new Color(102, 100, 204));
        sortByPriceLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        mainContent.add(sortByPriceLabel);
        
        sortByPrice.setBounds(275,50,120,30);
        sortByPrice.setVisible(true);
        sortByPrice.setFont(new Font("Arial", Font.BOLD ,15));
        sortByPrice.setBackground(new Color(30, 35, 60));
        sortByPrice.setForeground(Color.WHITE);
        sortByPrice.addActionListener(comboBoxesAction());
        mainContent.add(sortByPrice);
        
        
        sortByRatingLabel.setBounds(405, 50, 100, 30);                     
        sortByRatingLabel.setVisible(true);
        sortByRatingLabel.setForeground(new Color(102, 100, 204));
        sortByRatingLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        mainContent.add(sortByRatingLabel);
        
        sortByRating.setBounds(490,50,120,30);
        sortByRating.setVisible(true);
        sortByRating.setFont(new Font("Arial", Font.BOLD ,15));
        sortByRating.setBackground(new Color(30, 35, 60));
        sortByRating.setForeground(Color.WHITE);
        sortByRating.addActionListener(comboBoxesAction());
        mainContent.add(sortByRating);
        
        
        searchBarText.setBounds(630, 50, 220, 30);
        searchBarText.setBackground(new Color(10,25,40));
        searchBarText.setForeground(Color.CYAN);
        searchBarText.setFont(new Font("Courier New", Font.ITALIC, 20));
        searchBarText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchBarText.setVisible(true);
        searchBarText.addFocusListener(searchBarFocusListener(searchBarText));
        mainContent.add(searchBarText);
        
        searchBarButton.setBounds(850, 50, 30, 30);
        searchBarButton.setVisible(true);
        searchBarButton.setOpaque(true);                     
        searchBarButton.setContentAreaFilled(true);           
        searchBarButton.setBorderPainted(false);
        searchBarButton.setFocusPainted(false);
        searchBarButton.setBackground(new Color(10,25,40));
        searchBarButton.addMouseListener(searchButtonAction());
        mainContent.add(searchBarButton);
        
        
        platformLabel.setBounds(30, 100, 130, 30);                     
        platformLabel.setVisible(true);
        platformLabel.setForeground(new Color(102, 100, 204));
        platformLabel.setFont(new Font("Courier New", Font.BOLD, 23));
        mainContent.add(platformLabel);
        
        
        xBoxCheck.setVisible(true);
        xBoxCheck.setOpaque(false);
        xBoxCheck.setForeground(Color.CYAN);
        xBoxCheck.setFont(new Font("Courier New", Font.ITALIC, 17));
        xBoxCheck.setBounds(50, 140, 100, 30);
        xBoxCheck.setHorizontalTextPosition(SwingConstants.LEFT);
        xBoxCheck.addActionListener(checkBoxAction());
        mainContent.add(xBoxCheck);
        
        
        pSCheck.setVisible(true);
        pSCheck.setOpaque(false);
        pSCheck.setForeground(Color.CYAN);
        pSCheck.setFont(new Font("Courier New", Font.ITALIC, 17));
        pSCheck.setBounds(20, 180, 150, 30);
        pSCheck.setHorizontalTextPosition(SwingConstants.LEFT);
        pSCheck.addActionListener(checkBoxAction());
        mainContent.add(pSCheck);
        
        
        rangeLabel.setBounds(50, 230, 100, 30);                     
        rangeLabel.setVisible(true);
        rangeLabel.setForeground(new Color(102, 100, 204));
        rangeLabel.setFont(new Font("Courier New", Font.BOLD, 23));
        mainContent.add(rangeLabel);
        
        
        priceMinLabel.setBounds(20, 270, 140, 30);                     
        priceMinLabel.setVisible(true);
        priceMinLabel.setForeground(new Color(102, 100, 204));
        priceMinLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        mainContent.add(priceMinLabel);
        
        
        priceMaxLabel.setBounds(20, 310, 140, 30);                     
        priceMaxLabel.setVisible(true);
        priceMaxLabel.setForeground(new Color(102, 100, 204));
        priceMaxLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        mainContent.add(priceMaxLabel);
        
        
        priceMinText.setBounds(120, 270, 70, 30);
        priceMinText.setBackground(new Color(10,25,40));
        priceMinText.setForeground(Color.CYAN);
        priceMinText.setFont(new Font("Courier New", Font.ITALIC, 15));
        priceMinText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        priceMinText.setVisible(true);
        mainContent.add(priceMinText);
        
        
        priceMaxText.setBounds(120, 310, 70, 30);
        priceMaxText.setBackground(new Color(10,25,40));
        priceMaxText.setForeground(Color.CYAN);
        priceMaxText.setFont(new Font("Courier New", Font.ITALIC, 15));
        priceMaxText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        priceMaxText.setVisible(true);
        mainContent.add(priceMaxText);
        
        
        ratingMinLabel.setBounds(20, 350, 140, 30);                     
        ratingMinLabel.setVisible(true);
        ratingMinLabel.setForeground(new Color(102, 100, 204));
        ratingMinLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        mainContent.add(ratingMinLabel);
        
        
        ratingMaxLabel.setBounds(20, 390, 140, 30);                     
        ratingMaxLabel.setVisible(true);
        ratingMaxLabel.setForeground(new Color(102, 100, 204));
        ratingMaxLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        mainContent.add(ratingMaxLabel);
        
        
        ratingMinText.setBounds(120, 350, 70, 30);
        ratingMinText.setBackground(new Color(10,25,40));
        ratingMinText.setForeground(Color.CYAN);
        ratingMinText.setFont(new Font("Courier New", Font.ITALIC, 15));
        ratingMinText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        ratingMinText.setVisible(true);
        mainContent.add(ratingMinText);
        
        
        ratingMaxText.setBounds(120, 390, 70, 30);
        ratingMaxText.setBackground(new Color(10,25,40));
        ratingMaxText.setForeground(Color.CYAN);
        ratingMaxText.setFont(new Font("Courier New", Font.ITALIC, 15));
        ratingMaxText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        ratingMaxText.setVisible(true);
        mainContent.add(ratingMaxText);
        
        
        filterRange.setVisible(true);
        filterRange.setForeground(Color.CYAN);
        filterRange.setFont(new Font("Arial", Font.BOLD, 15));
        filterRange.setBackground(new Color(10,25,40));
        filterRange.setOpaque(true);
        filterRange.setContentAreaFilled(true);           
        filterRange.setBorderPainted(false);
        filterRange.setFocusPainted(false);
        filterRange.setBounds(10, 430, 180, 50);
        filterRange.addMouseListener(rangeAction());
        mainContent.add(filterRange);
        
        addItem.setForeground(Color.CYAN);
        addItem.setFont(new Font("Arial", Font.BOLD, 15));
        addItem.setBackground(new Color(10,60,40));
        addItem.setOpaque(true);
        addItem.setContentAreaFilled(true);           
        addItem.setBorderPainted(false);
        addItem.setFocusPainted(false);
        addItem.setBounds(10, 500, 180, 50);
        addItem.addMouseListener(addItemAction(addItem));
        mainContent.add(addItem);
        
        addProductPanel.setBounds(200, 100, 970, 600);
        mainContent.add(addProductPanel);
        
        
        refresh.setForeground(Color.CYAN);
        refresh.setFont(new Font("Arial", Font.BOLD, 15));
        refresh.setBackground(new Color(40,25,40));
        refresh.setOpaque(true);
        refresh.setContentAreaFilled(true);           
        refresh.setBorderPainted(false);
        refresh.setFocusPainted(false);
        refresh.setBounds(10, 600, 180, 50);
        refresh.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                 List<ProductItem> newList = new ArrayList<>();
                productsPanel.reloadAllProducts(newList);
            }
        });
        mainContent.add(refresh);
        
        
        
    }
    
    
    private boolean addItemClicked = false;
    private MouseAdapter addItemAction(JButton a){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                addItemClicked = !addItemClicked;
                if(addItemClicked){
                    productsPanel.setVisible(false);
                    addProductPanel.setVisible(true);
                    addProductPanel.cancelAddAndChangePhotoTrue();
                    a.setBackground(new Color(100,25,40));
                    a.setText("Cancel");
                }else{
                    addProductPanel.cancelAddAndChangePhotoFalse();
                    addProductPanel.setVisible(false);
                    productsPanel.setVisible(true);
                    a.setBackground(new Color(10,60,40));
                    a.setText("Add your product");
                }
            }
        };
    }
    
    private FocusListener searchBarFocusListener(JTextField a){
        return new FocusListener(){
        @Override
            public void focusGained(FocusEvent e) {
                if (a.getText().equals("Search")) {
                    a.setText("");
                }
            }

        @Override
            public void focusLost(FocusEvent e) {
                if (a.getText().trim().isEmpty()) {
                    a.setText("Search");
                }
            }
        };
    } 
    private ActionListener comboBoxesAction(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                List<ProductItem> newList = new ArrayList();
                newList.addAll(productsPanel.getGettingList());
                String type = (String) productType.getSelectedItem();
                String price = (String) sortByPrice.getSelectedItem();
                String rating = (String) sortByRating.getSelectedItem();
                productsPanel.filterProductListPanel(type, price, rating, newList);
            }
        };
    }
    private ActionListener checkBoxAction(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean xBox = xBoxCheck.isSelected();
                boolean ps = pSCheck.isSelected();
                List<ProductItem> newList = new ArrayList();
                newList.addAll(productsPanel.getGettingList());
                productsPanel.checkBoxFilter(xBox, ps, newList);
            }
            
        };
    }
    
    private MouseAdapter rangeAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                double priceMin = 0.0;
                double priceMax = 0.0;
                float ratingMin = 0;
                float ratingMax = 0;
                if(!priceMinText.getText().equals(""))
                   priceMin = Double.parseDouble(priceMinText.getText());
                if(!priceMaxText.getText().equals(""))
                    priceMax = Double.parseDouble(priceMaxText.getText());
                if(!ratingMinText.getText().equals(""))
                    ratingMin = Float.parseFloat(ratingMinText.getText());
                if(!ratingMaxText.getText().equals(""))   
                    ratingMax = Float.parseFloat(ratingMaxText.getText());
                
                List<ProductItem> newList = new ArrayList();
                newList.addAll(productsPanel.getGettingList());
                productsPanel.rangeFilter(priceMin, priceMax, ratingMin, ratingMax, newList);
                
                
            }
            
        };
    }
    
    public void setMainContentVisible(){
            mainContent.setVisible(true);
    }
    private MouseAdapter accountButtonAction(){  
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                mainContent.setVisible(false);
                financePanel.setVisible(false);
                usersPanel.setVisible(false);
                accountPanel.setVisible(true);
                sideMenuAnimation();
            }
        };
    }
    private MouseAdapter productsButtonAction(){  
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                financePanel.setVisible(false);
                accountPanel.setVisible(false);
                usersPanel.setVisible(false);
                mainContent.setVisible(true);
                sideMenuAnimation();
            }
        };
    }
    private MouseAdapter financeButtonAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mainContent.setVisible(false);
                accountPanel.setVisible(false);
                usersPanel.setVisible(false);
                financePanel.setVisible(true);
                sideMenuAnimation();
            }
        };
    }
    
    private MouseAdapter usersButtonAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mainContent.setVisible(false);
                accountPanel.setVisible(false);
                financePanel.setVisible(false);
                usersPanel.setVisible(true);
                sideMenuAnimation();
            }
        };
    }
    
    private MouseAdapter searchButtonAction(){  
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String searchString = searchBarText.getText();
                List<ProductItem> newList = new ArrayList<>();
                allProducts.clear();
                allProducts.addAll(productsPanel.reloadAllProducts(newList));
                productsPanel.searchProductList(searchString, allProducts);
            }
        };
    }
    
    private void addItemRefreshTrue(){
        if(Session.getCurrentUser().getRole().toLowerCase().equals("admin")){
            addItem.setVisible(true);
            refresh.setVisible(true);
        }
    }
    private void addItemRefreshFalse(){
        if(Session.getCurrentUser().getRole().toLowerCase().equals("admin")){
            addItem.setVisible(false);
            refresh.setVisible(false);
        }
    }
    
    private void sideMenuAnimation() {
        
        if (animationTimer != null && animationTimer.isRunning()) return;
        sideMenuOpen = !sideMenuOpen;
        animationTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int animationSpeed = 5;
                if (sideMenuOpen == true) {
                    sideMenuWidth += animationSpeed;
                    if(sideMenuWidth >= maxSideMenuWidth){
                        sideMenuWidth = maxSideMenuWidth;
                        animationTimer.stop();
                        sideMenuButton.setVisible(false);
                        xBoxCheck.setVisible(false);
                        pSCheck.setVisible(false);
                        filterRange.setVisible(false);
                        accountPanel.sideMenuOnSettings();
                        addItemRefreshFalse();
                        financePanel.applyRangeButtonFalse();
                    }      
                }else{
                    sideMenuWidth -= animationSpeed;
                    if(sideMenuWidth <= 0){
                        sideMenuWidth = 0;
                        animationTimer.stop();
                        sideMenuButton.setVisible(true);
                        xBoxCheck.setVisible(true);
                        pSCheck.setVisible(true);
                        filterRange.setVisible(true);
                        accountPanel.sideMenuOffSettings();
                        addItemRefreshTrue();
                        financePanel.applyRangeButtonTrue();
                    }
                }
                sideMenu.setBounds(0, 0, sideMenuWidth, getHeight());
                sideMenu.revalidate();
                sideMenu.repaint();
            }
        });
        animationTimer.start();
    }
    
    private FinancePanel financePanel = new FinancePanel();
    private AccountPanel accountPanel;
    private GradientPanel sideMenu = new GradientPanel(new Color(10,15,30), new Color(55, 40, 80), RADIAL);
    private JPanel mainContent = new JPanel();
    private GradientPanel topPanel = new GradientPanel(new Color(10, 15, 30), new Color(35, 40, 60), RADIAL);
    private IconButton sideMenuButton = new IconButton("images/sideMenuButtonIcon.png", 48, 48);
    private IconButton sideMenuCloseButton = new IconButton("images/backIcon.png",50, 50);
    private IconButton accountButton;
    private Timer animationTimer;
    private int sideMenuWidth = 0;
    private boolean sideMenuOpen = false;
    private final int maxSideMenuWidth = 150;
    private List<ProductItem> allProducts;
    private ProductsListPanel productsPanel;
    private JButton productsButton = new JButton("Products");
    private JButton financeButton = new JButton("Finances");
    private JButton usersButton = new JButton("Users");
    String[] productTypeString = { "All", "Videogames", "Movies" };
    private JComboBox<String> productType = new JComboBox<>(productTypeString);
    String[] priceSort = { "None", "Low to High" , "High to Low"};
    private JComboBox<String> sortByPrice = new JComboBox<>(priceSort);
    String[] ratingSort = { "None", "Low to High" , "High to Low"};
    private JComboBox<String> sortByRating = new JComboBox<>(ratingSort);
    private JLabel productTypeLabel = new JLabel("Type: ");
    private JLabel sortByPriceLabel = new JLabel("Price");
    private JLabel sortByRatingLabel = new JLabel("Rating");
    private JTextField searchBarText = new JTextField("Search");
    private IconButton searchBarButton = new IconButton("images/searchBarIcon.png", 30, 30);
    private JLabel platformLabel = new JLabel("Platform");
    private JCheckBox xBoxCheck = new JCheckBox("XBox");
    private JCheckBox pSCheck = new JCheckBox("Playstation");
    private JLabel rangeLabel = new JLabel("Ranges");
    private JLabel priceMinLabel = new JLabel("Min price");
    private JLabel priceMaxLabel = new JLabel("Max price");
    private JTextField priceMinText = new JTextField();
    private JTextField priceMaxText = new JTextField();
    private JLabel ratingMinLabel = new JLabel("Min rating");
    private JLabel ratingMaxLabel = new JLabel("Max rating");
    private JTextField ratingMinText = new JTextField();
    private JTextField ratingMaxText = new JTextField();
    private JButton filterRange = new JButton("Apply range filter");
    private JButton addItem = new JButton("Add your product");
    private AddProductPanel addProductPanel = new AddProductPanel();
    private JButton refresh = new JButton("Refresh");
    private UsersPanel usersPanel = new UsersPanel();
    
}
