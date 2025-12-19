package com.rwandamedia.system;

import com.rwandamedia.models.User;
import com.rwandamedia.models.Article;
import com.rwandamedia.models.Category;
import com.rwandamedia.models.Comment;
import com.rwandamedia.models.Advertisement;
import java.util.*;

public class InMemoryDatabase {
    private static Map<String, User> users = new HashMap<>();
    private static List<Article> articles = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static List<Comment> comments = new ArrayList<>();
    private static List<Advertisement> advertisements = new ArrayList<>();
    
    private static int articleIdCounter = 1;
    private static int categoryIdCounter = 1;
    private static int commentIdCounter = 1;
    private static int adIdCounter = 1;
    
    static {
        initializeData();
    }
    
    private static void initializeData() {
        System.out.println("=== Initializing Rwanda Media Platform In-Memory Database ===");
        
        Date currentDate = new Date();
        
        // Create sample users
        users.put("admin", new User(1, "admin", 
            PasswordHasher.hashPassword("password"), 
            "admin@rwandamedia.rw", "System Administrator", "Admin", currentDate, null));
            
        users.put("editor1", new User(2, "editor1", 
            PasswordHasher.hashPassword("password"), 
            "editor@newtimes.rw", "Jean de Dieu", "Editor", currentDate, null));
            
        users.put("viewer1", new User(3, "viewer1", 
            PasswordHasher.hashPassword("password"), 
            "reader@igihe.rw", "Marie Claire", "Viewer", currentDate, null));
        
        System.out.println("✓ Created " + users.size() + " users");
        
        // Create categories with Rwanda-specific content
        categories.add(new Category(1, "Politics", "Rwandan political news and government updates", 1, currentDate));
        categories.add(new Category(2, "Economy", "Economic developments and business news in Rwanda", 1, currentDate));
        categories.add(new Category(3, "Culture", "Rwandan culture, traditions and heritage", 1, currentDate));
        categories.add(new Category(4, "Tourism", "Tourism attractions and travel in Rwanda", 1, currentDate));
        categories.add(new Category(5, "Technology", "Tech innovations and ICT developments in Rwanda", 1, currentDate));
        categories.add(new Category(6, "Agriculture", "Agricultural news and developments in Rwanda", 1, currentDate));
        categories.add(new Category(7, "Education", "Education system and developments in Rwanda", 1, currentDate));
        
        System.out.println("✓ Created " + categories.size() + " categories");
        
        // Create articles with rich Rwanda-specific content
        articles.add(createArticle(
            "Rwanda Economic Growth Reaches 8.2% in 2024", 
            "Strong economic performance continues with impressive GDP growth", 
            "Rwanda's economy has shown remarkable resilience with GDP growth reaching 8.2% in the first quarter of 2024. The growth is largely driven by agriculture, tourism, and ICT sectors. Government initiatives like 'Made in Rwanda' and investment in infrastructure are paying significant dividends. The National Bank of Rwanda reports increased foreign direct investment and stable inflation rates.",
            2, "Published", 2
        ));
        
        articles.add(createArticle(
            "Kwita Izina 2024: 30 Baby Gorillas Named in Grand Ceremony", 
            "Annual gorilla naming ceremony celebrates conservation success", 
            "The 20th edition of Kwita Izina saw 30 baby mountain gorillas named in a colorful ceremony attended by conservationists, government officials, and local communities. The event, held at the foothills of Volcanoes National Park, highlights Rwanda's commitment to wildlife conservation. Since the program began, over 400 gorillas have been named, contributing to the growth of the endangered mountain gorilla population.",
            4, "Published", 2
        ));
        
        articles.add(createArticle(
            "Kigali Innovation City Attracts $100M in Tech Investments", 
            "Tech hub development accelerates with major international partnerships", 
            "Kigali Innovation City (KIC) has secured over $100 million in investments from international tech companies. The project, which aims to position Rwanda as a technology hub in Africa, has attracted companies like Andela, Google, and Microsoft. The first phase includes state-of-the-art facilities for tech education, innovation labs, and startup incubation centers focusing on AI, blockchain, and renewable energy technologies.",
            5, "Published", 2
        ));
        
        articles.add(createArticle(
            "Umuganda: Rwanda's Community Work Program Celebrates 25 Years", 
            "Monthly community service continues to transform neighborhoods", 
            "This month marks 25 years of Umuganda, Rwanda's national community work program. Every last Saturday of the month, citizens come together to work on community projects including road maintenance, building schools, and environmental conservation. The program has been credited with fostering unity and accelerating development across the country. Recent projects include the construction of 50 new classrooms in rural areas.",
            3, "Published", 2
        ));
        
        articles.add(createArticle(
            "Rwanda Launches New Agricultural Export Strategy", 
            "Focus on value addition and international market access", 
            "The Ministry of Agriculture has launched a comprehensive export strategy aimed at increasing agricultural exports by 50% over the next five years. The strategy focuses on coffee, tea, horticulture, and processed foods. New processing facilities are being established in rural areas to add value to raw agricultural products before export. The initiative is expected to create over 10,000 new jobs in the agricultural sector.",
            6, "Draft", 2
        ));
        
        articles.add(createArticle(
            "Visit Rwanda Campaign Boosts Tourism Revenue by 35%", 
            "International marketing efforts yield significant returns", 
            "The 'Visit Rwanda' international marketing campaign has resulted in a 35% increase in tourism revenue compared to last year. The partnership with major football clubs including Arsenal and PSG has significantly raised Rwanda's global profile. New luxury resorts and eco-lodges are opening across the country to accommodate the growing number of international visitors interested in gorilla trekking, cultural tourism, and conference tourism.",
            4, "Published", 2
        ));
        
        System.out.println("✓ Created " + articles.size() + " articles");
        
        // Create comments from users
        comments.add(new Comment(1, 1, 3, "This is fantastic news for our economy! The growth is impressive and shows the effectiveness of government policies. Keep up the good work!", currentDate));
        comments.add(new Comment(2, 2, 3, "Beautiful ceremony! So proud of our conservation efforts. The mountain gorillas are a national treasure that we must protect for future generations.", currentDate));
        comments.add(new Comment(3, 3, 3, "Great to see Rwanda becoming a tech hub in Africa. This will create amazing opportunities for our youth in the technology sector.", currentDate));
        comments.add(new Comment(4, 4, 3, "Umuganda has truly transformed our community. We recently built a health center through this program. It's wonderful to see everyone working together.", currentDate));
        comments.add(new Comment(5, 6, 3, "The tourism growth is amazing! I work in the hospitality industry and we've seen a significant increase in bookings from international visitors.", currentDate));
        
        System.out.println("✓ Created " + comments.size() + " comments");
        
        // Create advertisements
        advertisements.add(new Advertisement(1, "Visit Rwanda - Land of Thousand Hills", 
            "Experience the beauty of Rwanda - from mountain gorillas to vibrant cities. Discover why Rwanda is Africa's rising star in tourism and investment.", 
            "/images/visit_rwanda.jpg", "https://www.visitrwanda.com", "Active", 1, currentDate));
        
        advertisements.add(new Advertisement(2, "Kigali Convention Center", 
            "Host your next event at Africa's premier conference venue. State-of-the-art facilities in the heart of Kigali.", 
            "/images/kcc.jpg", "https://www.kigaliconvention.com", "Active", 1, currentDate));
        
        advertisements.add(new Advertisement(3, "Made in Rwanda Exhibition 2024", 
            "Support local products and manufacturers. Visit the annual Made in Rwanda exhibition showcasing the best of Rwandan innovation and craftsmanship.", 
            "/images/made_in_rwanda.jpg", "https://www.madeinrwanda.rw", "Active", 1, currentDate));
        
        advertisements.add(new Advertisement(4, "Rwanda Development Board - Investment Opportunities", 
            "Explore investment opportunities in Rwanda's growing economy. Special incentives available in ICT, manufacturing, and tourism sectors.", 
            "/images/rdb_investment.jpg", "https://www.rdb.rw", "Active", 1, currentDate));
        
        advertisements.add(new Advertisement(5, "Kigali Innovation City - Tech Hub", 
            "Join Africa's fastest growing tech ecosystem. Office spaces, incubation programs, and networking opportunities for tech startups and companies.", 
            "/images/kic_tech.jpg", "https://www.kigalinnovationcity.rw", "Active", 1, currentDate));
        
        System.out.println("✓ Created " + advertisements.size() + " advertisements");
        
        System.out.println("=== In-Memory Database Initialization Complete ===");
        System.out.println("Total Data Loaded:");
        System.out.println("  • Users: " + users.size());
        System.out.println("  • Categories: " + categories.size());
        System.out.println("  • Articles: " + articles.size());
        System.out.println("  • Comments: " + comments.size());
        System.out.println("  • Advertisements: " + advertisements.size());
        System.out.println("===================================================");
    }
    
    private static Article createArticle(String title, String description, String content, int categoryId, String status, int createdBy) {
        Article article = new Article();
        article.setArticleID(articleIdCounter++);
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setCategoryID(categoryId);
        article.setPriceOrValue(0.00);
        article.setStatus(status);
        article.setCreatedBy(createdBy);
        article.setCreatedAt(new Date());
        
        // Set category name
        if (categoryId >= 1 && categoryId <= categories.size()) {
            article.setCategoryName(categories.get(categoryId-1).getName());
        } else {
            article.setCategoryName("General");
        }
        
        // Set author name
        String authorKey = createdBy == 1 ? "admin" : createdBy == 2 ? "editor1" : "viewer1";
        if (users.containsKey(authorKey)) {
            article.setAuthorName(users.get(authorKey).getFullName());
        } else {
            article.setAuthorName("Unknown Author");
        }
        
        return article;
    }
    
    public static User authenticate(String username, String passwordHash) {
        User user = users.get(username);
        if (user != null && user.getPasswordHash().equals(passwordHash)) {
            System.out.println("✓ User authenticated: " + username + " (" + user.getFullName() + ")");
            return user;
        }
        System.out.println("✗ Authentication failed for: " + username);
        return null;
    }
    
    public static List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    public static List<Article> getAllArticles() {
        return new ArrayList<>(articles);
    }
    
    public static List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }
    
    public static List<Comment> getAllComments() {
        // Enhance comments with user and article information
        List<Comment> enhancedComments = new ArrayList<>();
        for (Comment comment : comments) {
            Comment enhanced = new Comment(
                comment.getCommentID(),
                comment.getArticleID(),
                comment.getUserID(),
                comment.getContent(),
                comment.getCreatedAt()
            );
            
            // Set user name
            String userKey = comment.getUserID() == 1 ? "admin" : comment.getUserID() == 2 ? "editor1" : "viewer1";
            if (users.containsKey(userKey)) {
                enhanced.setUserName(users.get(userKey).getFullName());
            } else {
                enhanced.setUserName("Unknown User");
            }
            
            // Set article title
            if (comment.getArticleID() >= 1 && comment.getArticleID() <= articles.size()) {
                enhanced.setArticleTitle(articles.get(comment.getArticleID()-1).getTitle());
            } else {
                enhanced.setArticleTitle("Unknown Article");
            }
            
            enhancedComments.add(enhanced);
        }
        return enhancedComments;
    }
    
    public static List<Advertisement> getAllAdvertisements() {
        // Enhance advertisements with creator information
        List<Advertisement> enhancedAds = new ArrayList<>();
        for (Advertisement ad : advertisements) {
            Advertisement enhanced = new Advertisement(
                ad.getAdvertisementID(),
                ad.getTitle(),
                ad.getDescription(),
                ad.getImageURL(),
                ad.getTargetURL(),
                ad.getStatus(),
                ad.getCreatedBy(),
                ad.getCreatedAt()
            );
            
            // Set creator name
            String creatorKey = ad.getCreatedBy() == 1 ? "admin" : "editor1";
            if (users.containsKey(creatorKey)) {
                enhanced.setCreatedByName(users.get(creatorKey).getFullName());
            } else {
                enhanced.setCreatedByName("System");
            }
            
            enhancedAds.add(enhanced);
        }
        return enhancedAds;
    }
    
    public static boolean updateLastLogin(int userId) {
        System.out.println("✓ Last login updated for user ID: " + userId);
        // In a real system, we would update the user's last login timestamp
        return true;
    }
    
    // Additional methods for data management
    public static int getTotalUsers() {
        return users.size();
    }
    
    public static int getTotalArticles() {
        return articles.size();
    }
    
    public static int getTotalCategories() {
        return categories.size();
    }
    
    public static int getTotalComments() {
        return comments.size();
    }
    
    public static int getTotalAdvertisements() {
        return advertisements.size();
    }
    
    // Method to get statistics for dashboard
    public static Map<String, Integer> getDashboardStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("users", getTotalUsers());
        stats.put("articles", getTotalArticles());
        stats.put("categories", getTotalCategories());
        stats.put("comments", getTotalComments());
        stats.put("advertisements", getTotalAdvertisements());
        return stats;
    }
    
    // Method to add a new article (for future use)
    public static boolean addArticle(Article article) {
        try {
            article.setArticleID(++articleIdCounter);
            articles.add(article);
            System.out.println("✓ New article added: " + article.getTitle());
            return true;
        } catch (Exception e) {
            System.err.println("✗ Error adding article: " + e.getMessage());
            return false;
        }
    }
    
    // Method to add a new comment (for future use)
    public static boolean addComment(Comment comment) {
        try {
            comment.setCommentID(++commentIdCounter);
            comments.add(comment);
            System.out.println("✓ New comment added by user: " + comment.getUserID());
            return true;
        } catch (Exception e) {
            System.err.println("✗ Error adding comment: " + e.getMessage());
            return false;
        }
    }
}