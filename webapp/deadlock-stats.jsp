<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deadlock 통계 - JSP 버전</title>
    <link rel="stylesheet" href="deadlock-stats.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🎮 Deadlock 통계</h1>
            <p class="subtitle">캐릭터 & 아이템 랭킹 (최근 7일)</p>
            <div id="last-update" class="last-update">
                <span>📅 마지막 업데이트: 
                    <%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %> (KST)
                </span>
            </div>
        </header>

        <nav class="navigation">
            <a href="index.jsp">홈</a>
            <a href="heroes.jsp">히어로</a>
            <a href="deadlock-stats.jsp" class="active">통계</a>
        </nav>

        <div class="stats-grid">
            <div class="stat-card">
                <h2 class="card-title">🏆 최고 플레이 캐릭터</h2>
                <div id="most-played-section">
                    <ul class="ranking-list">
                        <li class="ranking-item">
                            <span class="rank-number">1</span>
                            <div class="item-info">
                                <div class="hero-image">V</div>
                                <span class="character-name">Vindicta</span>
                            </div>
                            <span class="stat-value">15,243</span>
                        </li>
                        <li class="ranking-item">
                            <span class="rank-number">2</span>
                            <div class="item-info">
                                <div class="hero-image">A</div>
                                <span class="character-name">Abrams</span>
                            </div>
                            <span class="stat-value">13,827</span>
                        </li>
                        <li class="ranking-item">
                            <span class="rank-number">3</span>
                            <div class="item-info">
                                <div class="hero-image">W</div>
                                <span class="character-name">Wraith</span>
                            </div>
                            <span class="stat-value">12,456</span>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="stat-card">
                <h2 class="card-title">🥇 최고 승률 캐릭터</h2>
                <div id="win-rate-section">
                    <ul class="ranking-list">
                        <li class="ranking-item">
                            <span class="rank-number">1</span>
                            <div class="item-info">
                                <div class="hero-image">S</div>
                                <span class="character-name">Seven</span>
                            </div>
                            <span class="stat-value">67.8%</span>
                        </li>
                        <li class="ranking-item">
                            <span class="rank-number">2</span>
                            <div class="item-info">
                                <div class="hero-image">L</div>
                                <span class="character-name">Lash</span>
                            </div>
                            <span class="stat-value">65.2%</span>
                        </li>
                        <li class="ranking-item">
                            <span class="rank-number">3</span>
                            <div class="item-info">
                                <div class="hero-image">M</div>
                                <span class="character-name">McGinnis</span>
                            </div>
                            <span class="stat-value">62.4%</span>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="stat-card">
                <h2 class="card-title">⚔️ 최다 사용 아이템</h2>
                <div id="items-section">
                    <ul class="ranking-list">
                        <li class="ranking-item">
                            <span class="rank-number">1</span>
                            <div class="item-info">
                                <div class="item-image">💎</div>
                                <span class="item-name">Basic Magazine</span>
                            </div>
                            <span class="stat-value">84.7%</span>
                        </li>
                        <li class="ranking-item">
                            <span class="rank-number">2</span>
                            <div class="item-info">
                                <div class="item-image">🔫</div>
                                <span class="item-name">Monster Rounds</span>
                            </div>
                            <span class="stat-value">78.3%</span>
                        </li>
                        <li class="ranking-item">
                            <span class="rank-number">3</span>
                            <div class="item-info">
                                <div class="item-image">🛡️</div>
                                <span class="item-name">Extra Health</span>
                            </div>
                            <span class="stat-value">71.9%</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <script>
        console.log('=== Deadlock 통계 JSP 버전 ===');
        
        // 페이지 로드 완료 후 실행
        window.addEventListener('load', function() {
            console.log('✅ JSP 페이지 로딩 완료!');
        });
    </script>
</body>
</html>