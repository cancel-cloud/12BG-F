#!/usr/bin/env python3
"""
Script to update existing varieties in the coffee database with caffeine amounts.
Run this once after adding the caffeine_mg column to your database.
"""

import sqlite3

# Caffeine amounts per cup in mg
CAFFEINE_VALUES = {
    'Espresso': 64,
    'Americano': 154,
    'Latte': 154,
    'Cappuccino': 154,
    'Flat White': 154
}

def update_database():
    conn = sqlite3.connect('coffee.db')
    cursor = conn.cursor()
    
    # Check if caffeine_mg column exists
    cursor.execute("PRAGMA table_info(varieties)")
    columns = [row[1] for row in cursor.fetchall()]
    
    if 'caffeine_mg' not in columns:
        print("Adding caffeine_mg column to varieties table...")
        cursor.execute("ALTER TABLE varieties ADD COLUMN caffeine_mg INTEGER DEFAULT 0")
        conn.commit()
        print("✅ Column added successfully!")
    
    # Update existing varieties with caffeine values
    print("\nUpdating caffeine values for existing varieties...")
    
    for variety_name, caffeine_amount in CAFFEINE_VALUES.items():
        cursor.execute(
            "UPDATE varieties SET caffeine_mg = ? WHERE name = ?",
            (caffeine_amount, variety_name)
        )
        if cursor.rowcount > 0:
            print(f"✅ Updated {variety_name}: {caffeine_amount}mg")
        else:
            print(f"⚠️  {variety_name} not found in database")
    
    # Show all varieties with their caffeine amounts
    print("\nCurrent varieties in database:")
    cursor.execute("SELECT name, caffeine_mg FROM varieties ORDER BY name")
    varieties = cursor.fetchall()
    
    for name, caffeine in varieties:
        print(f"  {name}: {caffeine}mg")
    
    conn.commit()
    conn.close()
    
    print(f"\n🎉 Database update completed! Updated {len(CAFFEINE_VALUES)} varieties.")

if __name__ == "__main__":
    update_database() 