package com.irene.mycafe.data

data class MenuItem(
    val id: String,
    val name: String,
    val price: Long,
    val description: String,
    val imageUrl: String,
    val category: String,
    var userRating: Int = 0
)

data class RestaurantProfile(
    val name: String,
    val address: String,
    val description: String,
    val openingHours: String
)

val menuList = listOf(
    MenuItem("1", "Salad Buah Segar", 25000, "Campuran buah-buahan segar dengan dressing yogurt madu.", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("2", "Green Smoothie Bowl", 35000, "Smoothie bowl sayuran hijau dengan topping granola dan chia seeds.", "https://images.unsplash.com/photo-1511690656952-34342bb7c2f2?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("3", "Avocado Toast", 30000, "Roti gandum panggang dengan alpukat tumbuk dan telur rebus.", "https://images.unsplash.com/photo-1525351484163-7529414344d8?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("4", "Infused Water Lemon", 15000, "Air mineral dengan irisan lemon segar dan mint.", "https://images.unsplash.com/photo-1595981267035-7b04ca84a82d?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("5", "Kopi Susu Almond", 28000, "Kopi cold brew dengan susu almond tanpa pemanis buatan.", "https://images.unsplash.com/photo-1551024709-8f23befc6f87?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("6", "Grilled Chicken Salad", 45000, "Ayam panggang dengan sayuran organik dan saus lemon vinaigrette.", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("7", "Berry Yogurt Parfait", 22000, "Layer yogurt Yunani dengan beri segar dan madu.", "https://images.unsplash.com/photo-1488477181946-6428a0291777?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("8", "Salmon Poke Bowl", 65000, "Potongan salmon segar dengan nasi merah dan edamame.", "https://images.unsplash.com/photo-1513135065346-a098a63a71ee?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("9", "Chia Seed Pudding", 25000, "Puding biji chia dengan santan dan topping mangga.", "https://images.unsplash.com/photo-1510629954389-c1e0da47d414?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("10", "Matcha Latte", 32000, "Bubuk matcha Jepang berkualitas tinggi dengan susu oat.", "https://images.unsplash.com/photo-1515823064-d6e0c04616a7?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("11", "Quinoa Salad Bowl", 40000, "Quinoa campur dengan tomat ceri, timun, dan parsley.", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("12", "Fresh Orange Juice", 18000, "Perasan jeruk asli 100% tanpa gula tambahan.", "https://images.unsplash.com/photo-1613478223719-2ab802602423?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("13", "Vegetable Spring Rolls", 20000, "Lumpia sayuran segar tidak digoreng dengan saus kacang.", "https://images.unsplash.com/photo-1544434944-139b4d1c472a?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("14", "Green Tea Detox", 15000, "Teh hijau organik untuk meningkatkan metabolisme.", "https://images.unsplash.com/photo-1627435601361-ec25f5b1d0e5?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("15", "Oatmeal Cookies", 12000, "Biskuit gandum sehat dengan kismis dan tanpa mentega.", "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("16", "Lentil Soup", 35000, "Sup kacang lentil hangat dengan rempah-rempah pilihan.", "https://images.unsplash.com/photo-1547592166-23ac45744acd?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("17", "Ginger Honey Tea", 18000, "Teh jahe hangat dengan madu hutan murni.", "https://images.unsplash.com/photo-1513530176992-0cf39c4cbed4?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("18", "Tofu Salad", 28000, "Tofu panggang dengan selada dan wijen.", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?q=80&w=1000&auto=format&fit=crop", "Makanan"),
    MenuItem("19", "Coconut Water", 15000, "Air kelapa muda segar langsung dari buahnya.", "https://images.unsplash.com/photo-1543158031-64157173b2c2?q=80&w=1000&auto=format&fit=crop", "Minuman"),
    MenuItem("20", "Pumpkin Soup", 30000, "Sup labu kuning kental dan lembut.", "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?q=80&w=1000&auto=format&fit=crop", "Makanan")
)
