#!/bin/bash
set -e

echo "========================================"
echo "  工程项目管理 OA - 本地演示启动脚本"
echo "========================================"

# 检查 Docker
if ! command -v docker &> /dev/null; then
  echo "❌ 未检测到 Docker，请先安装 Docker Desktop："
  echo "   https://www.docker.com/products/docker-desktop/"
  exit 1
fi

if ! docker info &> /dev/null; then
  echo "❌ Docker 未启动，请先启动 Docker Desktop"
  exit 1
fi

echo "✅ Docker 已就绪"

# 进入 deploy 目录
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

echo ""
echo "📦 构建并启动所有服务（首次约 5-10 分钟）..."
docker compose up -d --build

echo ""
echo "⏳ 等待服务启动..."
sleep 5

# 等待后端就绪
echo "⏳ 等待后端就绪..."
until curl -s http://localhost:8078/actuator/health 2>/dev/null | grep -q '"UP"'; do
  printf "."
  sleep 3
done

echo ""
echo ""
echo "========================================"
echo "✅ 启动成功！"
echo ""
echo "🌐 访问地址：http://localhost"
echo "👤 账号：admin"
echo "🔑 密码：admin123"
echo "========================================"
echo ""
echo "停止服务：cd deploy && docker compose down"
echo "查看日志：cd deploy && docker compose logs -f backend"
