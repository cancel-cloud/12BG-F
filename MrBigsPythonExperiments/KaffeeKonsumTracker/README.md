# ☕ KaffeeKonsumTracker

A comprehensive coffee consumption tracking solution with two distinct implementations for different use cases.

## 📋 Project Overview

This repository contains **two separate coffee tracking applications**:

1. **[Personal Streamlit App](#-streamlit-app)** - Local single-user application
2. **[Multi-User Next.js App](./kaffeekonsum-tracker/)** - Cloud-based platform with Google authentication

## 🐍 Streamlit App

A feature-rich local coffee tracking application with German interface.

### Features
- ☕ **Consumption Logging** - Track daily coffee intake by variety
- ⚡ **Caffeine Monitoring** - Calculate daily caffeine intake with health warnings
- 📊 **Statistics & Analytics** - Daily/weekly/monthly consumption patterns
- 🗓️ **Calendar Heatmap** - Visual consumption history using calplot
- 🌱 **Variety Management** - Add/edit/delete coffee types with caffeine content
- 📁 **Data Import/Export** - CSV functionality for data portability
- 🎨 **Custom Styling** - Coffee-themed UI with responsive design

### Tech Stack
- **Framework**: Streamlit
- **Database**: SQLite (`coffee.db`)
- **Data Processing**: Pandas, SQLAlchemy
- **Visualizations**: Matplotlib, calplot
- **UI**: Custom CSS with coffee-themed styling

### Quick Start
```bash
# Install dependencies
pip install -r requirements.txt

# Run the application
streamlit run app.py
```

### Database Schema
- `varieties` table: Coffee types with caffeine content (mg/cup)
- `consumption` table: Daily consumption logs (date, cups, variety)

## 🌐 Multi-User Next.js App

A modern web platform for coffee tracking with Google authentication and multi-user support.

**[📁 Navigate to Next.js App →](./kaffeekonsum-tracker/)**

### Features
- 🔐 **Google Authentication** - Secure login via Appwrite + Google OAuth
- 👥 **Multi-User Platform** - Each user maintains separate consumption logs
- 📊 **Real-Time Dashboard** - Personal statistics with interactive charts
- 🛠️ **Admin Management** - Global coffee variety management
- 📱 **Responsive Design** - Modern UI with Tailwind CSS + shadcn/ui
- 📈 **Advanced Visualizations** - Bar charts, pie charts, calendar heatmap
- 💾 **Cloud Storage** - Appwrite backend with real-time data sync
- 📤 **CSV Import/Export** - Data portability and backup features

### Tech Stack
- **Frontend**: Next.js 15, React 19, TypeScript
- **Backend**: Appwrite (BaaS)
- **Authentication**: Google OAuth via Appwrite
- **UI Framework**: Tailwind CSS, shadcn/ui, Lucide React
- **Charts**: Chart.js, react-calendar-heatmap
- **Database**: Appwrite Database (NoSQL)

### Quick Start
```bash
cd kaffeekonsum-tracker
npm install
npm run dev
```

### Data Structure
- **Users**: Google OAuth authentication
- **Varieties Collection**: Global coffee types (admin managed)
- **Consumption Collection**: Per-user consumption logs
- **Admin Features**: Special permissions for variety management

## 🔗 Project Navigation

| Component | Description | Link |
|-----------|-------------|------|
| **Streamlit App** | Personal local tracker | `app.py` (root) |
| **Next.js App** | Multi-user platform | **[./kaffeekonsum-tracker/](./kaffeekonsum-tracker/)** |
| **Database Migration** | Caffeine content updater | `update_caffeine.py` |
| **Sample Data** | Coffee varieties | `variants.csv` |

## 🚀 Use Cases

### Choose Streamlit App When:
- ✅ Personal use only
- ✅ Offline functionality needed
- ✅ Simple local data storage
- ✅ No authentication required
- ✅ Quick setup and deployment

### Choose Next.js App When:
- ✅ Multiple users needed
- ✅ Cloud synchronization required
- ✅ Modern web interface preferred
- ✅ Google authentication integration
- ✅ Scalable platform needed

## 📊 Coffee Varieties Supported

Both applications support tracking various coffee types:
- ☕ **Espresso** (64mg caffeine)
- ☕ **Americano** (154mg caffeine)
- ☕ **Latte** (154mg caffeine)
- ☕ **Cappuccino** (154mg caffeine)
- ☕ **Flat White** (154mg caffeine)

*Caffeine values are customizable in both applications*

## 🛠️ Development

### Streamlit App Development
```bash
# Install dependencies
pip install -r requirements.txt

# Run with auto-reload
streamlit run app.py --server.runOnSave true
```

### Next.js App Development
```bash
cd kaffeekonsum-tracker
npm run dev --turbopack
```

## 📝 License

This project is open source and available under the MIT License.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for either application.

---

**Happy Coffee Tracking! ☕📈** 