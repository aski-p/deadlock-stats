<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deadlock 히어로 - JSP 버전</title>
    <link rel="stylesheet" href="deadlock-stats.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🦸 Deadlock 히어로</h1>
            <p class="subtitle">캐릭터 상세 정보 (최근 7일)</p>
            <div id="last-update" class="last-update">
                <span>📅 마지막 업데이트: 
                    <%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %> (KST)
                </span>
            </div>
        </header>

        <nav class="navigation">
            <a href="index.jsp">홈</a>
            <a href="heroes.jsp" class="active">히어로</a>
            <a href="deadlock-stats.jsp">통계</a>
        </nav>

        <div class="stats-grid">
            <div class="stat-card">
                <h2 class="card-title">🔥 인기 히어로</h2>
                <div class="hero-grid">
                    <div class="hero-card">
                        <div class="hero-image large">V</div>
                        <h3>Vindicta</h3>
                        <p>플레이 횟수: 15,243</p>
                        <p>승률: 58.7%</p>
                    </div>
                    <div class="hero-card">
                        <div class="hero-image large">A</div>
                        <h3>Abrams</h3>
                        <p>플레이 횟수: 13,827</p>
                        <p>승률: 61.2%</p>
                    </div>
                    <div class="hero-card">
                        <div class="hero-image large">W</div>
                        <h3>Wraith</h3>
                        <p>플레이 횟수: 12,456</p>
                        <p>승률: 55.9%</p>
                    </div>
                </div>
            </div>

            <div class="stat-card">
                <h2 class="card-title">🏆 최고 승률 히어로</h2>
                <div class="hero-grid">
                    <div class="hero-card">
                        <div class="hero-image large">S</div>
                        <h3>Seven</h3>
                        <p>플레이 횟수: 8,942</p>
                        <p>승률: 67.8%</p>
                    </div>
                    <div class="hero-card">
                        <div class="hero-image large">L</div>
                        <h3>Lash</h3>
                        <p>플레이 횟수: 7,654</p>
                        <p>승률: 65.2%</p>
                    </div>
                    <div class="hero-card">
                        <div class="hero-image large">M</div>
                        <h3>McGinnis</h3>
                        <p>플레이 횟수: 6,832</p>
                        <p>승률: 62.4%</p>
                    </div>
                </div>
            </div>

            <div class="stat-card">
                <h2 class="card-title">📊 히어로 분석</h2>
                <div class="analysis-section">
                    <div class="analysis-item">
                        <h4>💪 탱커 히어로</h4>
                        <p>Abrams, McGinnis, Dynamo</p>
                        <span class="stat-value">평균 승률: 60.3%</span>
                    </div>
                    <div class="analysis-item">
                        <h4>🎯 딜러 히어로</h4>
                        <p>Vindicta, Wraith, Haze</p>
                        <span class="stat-value">평균 승률: 57.8%</span>
                    </div>
                    <div class="analysis-item">
                        <h4>🛡️ 서포터 히어로</h4>
                        <p>Kelvin, Ivy, Yamato</p>
                        <span class="stat-value">평균 승률: 55.2%</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        console.log('=== Deadlock 히어로 JSP 버전 ===');
        
        // 페이지 로드 완료 후 실행
        window.addEventListener('load', function() {
            console.log('✅ Heroes JSP 페이지 로딩 완료!');
        });
    </script>
</body>
</html>