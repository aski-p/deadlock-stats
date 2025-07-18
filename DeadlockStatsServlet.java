package com.deadlock.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DeadlockStatsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // 캐릭터 ID별 영어 이름 매핑 (deadlock-api.com 기준)
    private static final Map<Integer, String> HERO_NAMES = new HashMap<>();
    static {
        HERO_NAMES.put(1, "Infernus");
        HERO_NAMES.put(2, "Seven");
        HERO_NAMES.put(3, "Vindicta");
        HERO_NAMES.put(4, "Lady Geist");
        HERO_NAMES.put(6, "Abrams");
        HERO_NAMES.put(7, "Wraith");
        HERO_NAMES.put(8, "McGinnis");
        HERO_NAMES.put(10, "Paradox");
        HERO_NAMES.put(11, "Dynamo");
        HERO_NAMES.put(12, "Kelvin");
        HERO_NAMES.put(13, "Haze");
        HERO_NAMES.put(14, "Holliday");
        HERO_NAMES.put(15, "Bebop");
        HERO_NAMES.put(16, "Calico");
        HERO_NAMES.put(17, "Grey Talon");
        HERO_NAMES.put(18, "Mo & Krill");
        HERO_NAMES.put(19, "Shiv");
        HERO_NAMES.put(20, "Ivy");
        HERO_NAMES.put(21, "Kali");
        HERO_NAMES.put(25, "Warden");
        HERO_NAMES.put(27, "Yamato");
        HERO_NAMES.put(31, "Lash");
        HERO_NAMES.put(35, "Viscous");
        HERO_NAMES.put(38, "Gunslinger");
        HERO_NAMES.put(39, "Boss");
        HERO_NAMES.put(47, "Tokamak");
        HERO_NAMES.put(48, "Wrecker");
        HERO_NAMES.put(49, "Rutger");
        HERO_NAMES.put(50, "Pocket");
        HERO_NAMES.put(51, "Thumper");
        HERO_NAMES.put(52, "Mirage");
        HERO_NAMES.put(53, "Fathom");
        HERO_NAMES.put(54, "Cadence");
        HERO_NAMES.put(56, "Bomber");
        HERO_NAMES.put(57, "Shield Guy");
        HERO_NAMES.put(58, "Viper");
        HERO_NAMES.put(59, "Vandal");
        HERO_NAMES.put(60, "Sinclair");
        HERO_NAMES.put(61, "Trapper");
        HERO_NAMES.put(62, "Raven");
        HERO_NAMES.put(63, "Vampire Bat");
        HERO_NAMES.put(64, "Drifter");
        HERO_NAMES.put(65, "Priest");
        HERO_NAMES.put(66, "Frank");
        HERO_NAMES.put(67, "Bookworm");
        HERO_NAMES.put(68, "Protector");
        HERO_NAMES.put(69, "Doorman");
        HERO_NAMES.put(70, "Skyrunner");
        HERO_NAMES.put(71, "Swan");
        HERO_NAMES.put(72, "Punkgoth");
    }
    
    // 아이템 한글 이름 매핑
    private static final Map<String, String> ITEM_KOREAN_NAMES = new HashMap<>();
    static {
        ITEM_KOREAN_NAMES.put("Superior Cooldown", "상급 쿨다운 감소");
        ITEM_KOREAN_NAMES.put("Extra Regen", "추가 재생");
        ITEM_KOREAN_NAMES.put("Compress Cooldown", "압축 쿨다운");
        ITEM_KOREAN_NAMES.put("Extra Spirit", "추가 정신력");
        ITEM_KOREAN_NAMES.put("Fortitude", "불굴");
        ITEM_KOREAN_NAMES.put("Extra Stamina", "추가 스태미나");
        ITEM_KOREAN_NAMES.put("Extra Health", "추가 체력");
        ITEM_KOREAN_NAMES.put("Improved Spirit", "개선된 정신력");
        ITEM_KOREAN_NAMES.put("Quicksilver Reload", "수은 재장전");
        ITEM_KOREAN_NAMES.put("Mystic Burst", "신비 점사");
        ITEM_KOREAN_NAMES.put("Enchanter's Emblem", "마법사 엠블럼");
        ITEM_KOREAN_NAMES.put("Rapid Rounds", "빠른 탄환");
        ITEM_KOREAN_NAMES.put("Extra Charge", "추가 충전");
        ITEM_KOREAN_NAMES.put("Extended Magazine", "확장 탄창");
        ITEM_KOREAN_NAMES.put("Titanic Magazine", "타이타닉 탄창");
        ITEM_KOREAN_NAMES.put("Improved Cooldown", "개선된 쿨다운 감소");
        ITEM_KOREAN_NAMES.put("Melee Charge", "근접 돌격");
        ITEM_KOREAN_NAMES.put("Basic Magazine", "기본 탄창");
        ITEM_KOREAN_NAMES.put("Bullet Armor", "총알 방어구");
        ITEM_KOREAN_NAMES.put("Spirit Armor", "정신 방어구");
        ITEM_KOREAN_NAMES.put("Healing Rite", "치유 의식");
        ITEM_KOREAN_NAMES.put("Sprint Boots", "질주 부츠");
        ITEM_KOREAN_NAMES.put("Enduring Speed", "지속 속도");
        ITEM_KOREAN_NAMES.put("Monster Rounds", "몬스터 탄환");
        ITEM_KOREAN_NAMES.put("High-Velocity Mag", "고속 탄창");
        ITEM_KOREAN_NAMES.put("Headshot Booster", "헤드샷 부스터");
        ITEM_KOREAN_NAMES.put("Kinetic Dash", "운동 돌진");
        ITEM_KOREAN_NAMES.put("Combat Barrier", "전투 방벽");
        ITEM_KOREAN_NAMES.put("Debuff Reducer", "디버프 감소");
        ITEM_KOREAN_NAMES.put("Divine Barrier", "신성 방벽");
        ITEM_KOREAN_NAMES.put("Healing Booster", "치유 부스터");
        ITEM_KOREAN_NAMES.put("Mystic Vulnerability", "신비 취약점");
        ITEM_KOREAN_NAMES.put("Return Fire", "반격 사격");
        ITEM_KOREAN_NAMES.put("Reactive Barrier", "반응형 방벽");
        ITEM_KOREAN_NAMES.put("Restorative Shot", "회복 사격");
        ITEM_KOREAN_NAMES.put("Soul Shredder Bullets", "영혼 파쇄 탄환");
        ITEM_KOREAN_NAMES.put("Explosive Rounds", "폭발 탄환");
        ITEM_KOREAN_NAMES.put("Toxic Bullets", "독성 탄환");
        ITEM_KOREAN_NAMES.put("Slowing Bullets", "둔화 탄환");
        ITEM_KOREAN_NAMES.put("Tesla Bullets", "테슬라 탄환");
        ITEM_KOREAN_NAMES.put("Hollow Point Ward", "중공탄 방어");
        ITEM_KOREAN_NAMES.put("Burst Fire", "점사");
        ITEM_KOREAN_NAMES.put("Close Quarters", "근접 전투");
        ITEM_KOREAN_NAMES.put("Long Range", "장거리");
        ITEM_KOREAN_NAMES.put("Pristine Emblem", "완벽한 엠블럼");
        ITEM_KOREAN_NAMES.put("Vitality Booster", "생명력 부스터");
        ITEM_KOREAN_NAMES.put("Spirit Power", "정신력");
        ITEM_KOREAN_NAMES.put("Weapon Damage", "무기 데미지");
        ITEM_KOREAN_NAMES.put("Ammo Scavenger", "탄약 수집가");
        ITEM_KOREAN_NAMES.put("Bullet Lifesteal", "총알 생명력 흡수");
        ITEM_KOREAN_NAMES.put("Spirit Lifesteal", "정신 생명력 흡수");
        ITEM_KOREAN_NAMES.put("Melee Lifesteal", "근접 생명력 흡수");
    }
    
    // 데이터 모델 클래스들
    public static class HeroStat {
        private String name;
        private int matches;
        private double winRate;
        private double kills;
        private double deaths;
        private double assists;
        private String image;
        
        public HeroStat(String name, int matches, double winRate, String image) {
            this.name = name;
            this.matches = matches;
            this.winRate = winRate;
            this.kills = 0.0;
            this.deaths = 0.0;
            this.assists = 0.0;
            this.image = image;
        }
        
        public HeroStat(String name, int matches, double winRate, double kills, double deaths, double assists, String image) {
            this.name = name;
            this.matches = matches;
            this.winRate = winRate;
            this.kills = kills;
            this.deaths = deaths;
            this.assists = assists;
            this.image = image;
        }
        
        // Getters
        public String getName() { return name; }
        public int getMatches() { return matches; }
        public double getWinRate() { return winRate; }
        public double getKills() { return kills; }
        public double getDeaths() { return deaths; }
        public double getAssists() { return assists; }
        public String getImage() { return image; }
    }
    
    public static class ItemStat {
        private String name;
        private double usageRate;
        private String image;
        
        public ItemStat(String name, double usageRate, String image) {
            this.name = name;
            this.usageRate = usageRate;
            this.image = image;
        }
        
        // Getters
        public String getName() { return name; }
        public double getUsageRate() { return usageRate; }
        public String getImage() { return image; }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 7일 전 타임스탬프 계산
            long sevenDaysAgo = System.currentTimeMillis() / 1000 - (7 * 24 * 60 * 60);
            
            // 랭크 파라미터 읽기
            String minRankParam = request.getParameter("min_rank");
            String maxRankParam = request.getParameter("max_rank");
            
            // 기본값 설정 (Phantom 1 ~ Eternus 6)
            int minRank = minRankParam != null ? Integer.parseInt(minRankParam) : 8;
            int maxRank = maxRankParam != null ? Integer.parseInt(maxRankParam) : 25;
            
            // 캐릭터 이미지 데이터 로드
            Map<Integer, String> heroImages = loadHeroImages();
            
            // 아이템 매핑 데이터 로드
            Map<Integer, String> itemNames = new HashMap<>();
            Map<Integer, String> itemImages = new HashMap<>();
            loadItemMappings(itemNames, itemImages);
            
            // API에서 데이터 가져오기 (랭크 필터 적용)
            List<HeroStat> heroStats = fetchHeroStats(sevenDaysAgo, heroImages, minRank, maxRank);
            List<ItemStat> itemStats = fetchItemStats(sevenDaysAgo, itemNames, itemImages);
            
            // 데이터 정렬 및 상위 8개 추출
            List<HeroStat> mostPlayedHeroes = heroStats.stream()
                    .sorted((a, b) -> Integer.compare(b.getMatches(), a.getMatches()))
                    .limit(8)
                    .collect(Collectors.toList());
            
            List<HeroStat> bestWinRateHeroes = heroStats.stream()
                    .sorted((a, b) -> Double.compare(b.getWinRate(), a.getWinRate()))
                    .limit(8)
                    .collect(Collectors.toList());
            
            List<ItemStat> topItems = itemStats.stream()
                    .sorted((a, b) -> Double.compare(b.getUsageRate(), a.getUsageRate()))
                    .limit(8)
                    .collect(Collectors.toList());
            
            // JSP로 데이터 전달
            request.setAttribute("mostPlayedHeroes", mostPlayedHeroes);
            request.setAttribute("bestWinRateHeroes", bestWinRateHeroes);
            request.setAttribute("topItems", topItems);
            request.setAttribute("allHeroes", heroStats); // 모든 영웅 데이터 추가
            
            // URL 패턴에 따라 다른 JSP로 포워딩
            String path = request.getRequestURI();
            if (path.contains("/heroes")) {
                request.getRequestDispatcher("/heroes.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/deadlock-stats.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                    "데이터 로딩 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    private Map<Integer, String> loadHeroImages() throws IOException {
        Map<Integer, String> heroImages = new HashMap<>();
        
        try {
            String jsonResponse = fetchFromAPI("https://assets.deadlock-api.com/v2/heroes");
            JsonArray heroes = JsonParser.parseString(jsonResponse).getAsJsonArray();
            
            for (JsonElement heroElement : heroes) {
                JsonObject hero = heroElement.getAsJsonObject();
                int heroId = hero.get("id").getAsInt();
                
                // API에서 받은 영어 이름으로 HERO_NAMES 맵 업데이트
                if (hero.has("name")) {
                    String heroName = hero.get("name").getAsString();
                    HERO_NAMES.put(heroId, heroName);
                }
                
                JsonObject images = hero.getAsJsonObject("images");
                if (images != null && images.has("icon_image_small")) {
                    String imageUrl = images.get("icon_image_small").getAsString();
                    heroImages.put(heroId, imageUrl);
                }
            }
        } catch (Exception e) {
            System.err.println("캐릭터 이미지 로드 실패: " + e.getMessage());
        }
        
        return heroImages;
    }
    
    private void loadItemMappings(Map<Integer, String> itemNames, Map<Integer, String> itemImages) 
            throws IOException {
        
        try {
            String jsonResponse = fetchFromAPI("https://assets.deadlock-api.com/v2/items");
            JsonArray items = JsonParser.parseString(jsonResponse).getAsJsonArray();
            
            for (JsonElement itemElement : items) {
                JsonObject item = itemElement.getAsJsonObject();
                int itemId = item.get("id").getAsInt();
                
                // 아이템 이름 매핑
                String itemName = item.get("name").getAsString();
                String koreanName = ITEM_KOREAN_NAMES.getOrDefault(itemName, itemName);
                itemNames.put(itemId, koreanName);
                
                // 아이템 이미지 매핑
                if (item.has("shop_image")) {
                    itemImages.put(itemId, item.get("shop_image").getAsString());
                } else if (item.has("image")) {
                    itemImages.put(itemId, item.get("image").getAsString());
                }
            }
        } catch (Exception e) {
            System.err.println("아이템 매핑 로드 실패: " + e.getMessage());
        }
    }
    
    private List<HeroStat> fetchHeroStats(long sevenDaysAgo, Map<Integer, String> heroImages, int minRank, int maxRank) 
            throws IOException {
        
        List<HeroStat> heroStats = new ArrayList<>();
        
        String apiUrl = "https://api.deadlock-api.com/v1/analytics/hero-stats?min_unix_timestamp=" + sevenDaysAgo 
                       + "&min_rank=" + minRank + "&max_rank=" + maxRank;
        String jsonResponse = fetchFromAPI(apiUrl);
        
        JsonArray heroes = JsonParser.parseString(jsonResponse).getAsJsonArray();
        
        for (JsonElement heroElement : heroes) {
            JsonObject hero = heroElement.getAsJsonObject();
            
            int heroId = hero.get("hero_id").getAsInt();
            int matches = hero.has("matches") ? hero.get("matches").getAsInt() : 0;
            int wins = hero.has("wins") ? hero.get("wins").getAsInt() : 0;
            
            // KDA 데이터 추출 및 평균 계산
            long totalKills = hero.has("total_kills") ? hero.get("total_kills").getAsLong() : 0;
            long totalDeaths = hero.has("total_deaths") ? hero.get("total_deaths").getAsLong() : 0;
            long totalAssists = hero.has("total_assists") ? hero.get("total_assists").getAsLong() : 0;
            
            double avgKills = matches > 0 ? (double) totalKills / matches : 0.0;
            double avgDeaths = matches > 0 ? (double) totalDeaths / matches : 0.0;
            double avgAssists = matches > 0 ? (double) totalAssists / matches : 0.0;
            
            String heroName = HERO_NAMES.getOrDefault(heroId, "캐릭터 " + heroId);
            double winRate = matches > 0 ? (double) wins / matches * 100 : 0.0;
            String imageUrl = heroImages.getOrDefault(heroId, "");
            
            heroStats.add(new HeroStat(heroName, matches, winRate, avgKills, avgDeaths, avgAssists, imageUrl));
        }
        
        return heroStats;
    }
    
    private List<ItemStat> fetchItemStats(long sevenDaysAgo, Map<Integer, String> itemNames, 
                                          Map<Integer, String> itemImages) throws IOException {
        
        List<ItemStat> itemStats = new ArrayList<>();
        
        String apiUrl = "https://api.deadlock-api.com/v1/analytics/item-stats?min_unix_timestamp=" + sevenDaysAgo;
        String jsonResponse = fetchFromAPI(apiUrl);
        
        JsonArray items = JsonParser.parseString(jsonResponse).getAsJsonArray();
        
        // 최대 매치 수 계산
        int maxMatches = 0;
        for (JsonElement itemElement : items) {
            JsonObject item = itemElement.getAsJsonObject();
            int matches = item.has("matches") ? item.get("matches").getAsInt() : 0;
            maxMatches = Math.max(maxMatches, matches);
        }
        
        for (JsonElement itemElement : items) {
            JsonObject item = itemElement.getAsJsonObject();
            
            int itemId = item.get("item_id").getAsInt();
            int matches = item.has("matches") ? item.get("matches").getAsInt() : 0;
            
            String itemName = itemNames.getOrDefault(itemId, "아이템 " + itemId);
            double usageRate = maxMatches > 0 ? (double) matches / maxMatches * 100 : 0.0;
            String imageUrl = itemImages.getOrDefault(itemId, "");
            
            itemStats.add(new ItemStat(itemName, usageRate, imageUrl));
        }
        
        return itemStats;
    }
    
    private String fetchFromAPI(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        
        if (conn.getResponseCode() != 200) {
            throw new IOException("API 호출 실패: " + conn.getResponseCode());
        }
        
        try (InputStream inputStream = conn.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {
            
            StringBuilder response = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;
            
            while ((bytesRead = reader.read(buffer)) != -1) {
                response.append(buffer, 0, bytesRead);
            }
            
            return response.toString();
        }
    }
}