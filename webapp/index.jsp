<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deadlock Stats</title>
    <link rel="stylesheet" href="deadlock-stats.css">
    <style>
        .welcome-section {
            background: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 15px;
            margin: 20px 0;
            text-align: center;
        }
        .version-info {
            background: rgba(0, 0, 0, 0.2);
            padding: 15px;
            border-radius: 10px;
            margin: 20px 0;
        }
        .navigation {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin: 30px 0;
        }
        .navigation a {
            padding: 15px 30px;
            background: rgba(255, 255, 255, 0.2);
            color: white;
            text-decoration: none;
            border-radius: 10px;
            transition: all 0.3s ease;
            font-weight: bold;
        }
        .navigation a:hover {
            background: rgba(255, 255, 255, 0.3);
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>🎮 Deadlock Stats Application</h1>
            <p class="subtitle">Railway에서 실행 중인 JSP 애플리케이션</p>
        </header>

        <div class="welcome-section">
            <h2>✅ JSP Server is running!</h2>
            <p>현재 시간: <%= new java.util.Date().toString() %></p>
            <p>Java 버전: <%= System.getProperty("java.version") %></p>
        </div>

        <nav class="navigation">
            <a href="heroes.jsp">🦸 히어로 페이지</a>
            <a href="deadlock-stats.jsp">📊 통계 페이지</a>
        </nav>

        <div class="version-info">
            <h3>📋 애플리케이션 정보</h3>
            <p><strong>플랫폼:</strong> Railway</p>
            <p><strong>서버:</strong> Apache Tomcat</p>
            <p><strong>언어:</strong> Java JSP</p>
            <p><strong>상태:</strong> 정상 실행 중</p>
        </div>

        <div class="stats-grid">
            <div class="stat-card">
                <h2 class="card-title">🚀 빠른 접속</h2>
                <div class="quick-links">
                    <a href="heroes.jsp" class="quick-link">히어로 정보 보기</a>
                    <a href="deadlock-stats.jsp" class="quick-link">게임 통계 보기</a>
                </div>
            </div>
        </div>
    </div>

    <script>
        console.log('=== Deadlock Stats JSP 메인 페이지 ===');
        
        // 페이지 로드 완료 후 실행
        window.addEventListener('load', function() {
            console.log('✅ 메인 JSP 페이지 로딩 완료!');
            console.log('Railway URL에서 정상 실행 중');
        });
    </script>
</body>
</html>