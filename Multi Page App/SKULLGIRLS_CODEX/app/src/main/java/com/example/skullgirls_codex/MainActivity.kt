package com.example.skullgirls_codex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var adapter: FighterAdapter
    lateinit var fighters: List<Fighter>
    private var showingFavorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchBar = findViewById<EditText>(R.id.searchBar)
        val favoritesButton = findViewById<Button>(R.id.favoritesButton)

        fighters = listOf(

            Fighter(
                "Filia",
                R.drawable.filia,
                "Filia Medici (JP: フィリア・メディチ Firia Medichi) was once a somewhat normal school girl, until her parents were killed by Vitale and Ottomo, with Samson fusing with her to save her life. She is currently homeless and suffering from amnesia.\n" +
                        "\n" +
                        "She is the daughter of Amelia and Marcus, making her Lorenzo's granddaughter and Vitale's niece.",
                "Easy",
                "Rushdown"
            ),

            Fighter(
                "Cerebella",
                R.drawable.cerebella,
                "Cerebella (JP: セレベラ Serebera) is the alias of Cassandra Veranos (JP: カサンドラ・ベラノス Kasandora Beranosu), a survivor of No Man's Land who was adopted by the Medici and is now a member of the Cirque des Cartes and wielder of the Living Weapon, Vice-Versa.",
                "Medium",
                "Grappler"
            ),

            Fighter(
                "Peacock",
                R.drawable.peacock,
                "Peacock (JP: ピーコック Pīkokku) is the alias of Patricia Watson (JP: パトリシア・ワトソン Patorishia Watoson), she is a survivor of No Man's Land who was rescued by Lab 8 and turned into an Anti-Skullgirl weapon by Dr. Avian.",
                "Hard",
                "Zoner"
            ),
            Fighter(
                "Parasoul",
                R.drawable.parasoul,
                "Parasoul Renoir (JP: パラソール・ルノワール Parasōru Runowāru) is the princess of the Renoir family, the rulers of the Canopy Kingdom, and the older sister of Umbrella. She is the daughter of King Franz and Queen Nancy, the previous Skullgirl, and wields the Living Weapon, Krieg, in battle. Under her command are the Black Egrets.",
                "Easy",
                "Zoner/Setup"
            ),
            Fighter(
                "Ms. Fortune",
                R.drawable.ms_fortune,
                "Ms. Fortune (JP: ミス・フォーチュン Misu Fōchun), also referred to by her full name, Nadia Fortune (JP: ナディア・フォーチュン Nadia Fōchun), is a feline Feral who belonged to the Fishbone Gang, she was rendered undying when she swallowed the Life Gem she stole.",
                "Hard",
                "Puppet"
            ),
            Fighter(
                "Painwheel",
                R.drawable.painwheel,
                "Painwheel (JP: ペインホイール Peinhoīru) was once a school girl named Carol (JP: キャロル Kyaroru) before she was kidnapped and transformed into an Anti-Skullgirl weapon by Valentine and Brain Drain.",
                "Hard",
                "Glass-canon"
            ),
            Fighter(
                "Valentine",
                R.drawable.valentine,
                "Valerie (JP: ヴァレリー Varerī), code name Valentine (JP: ヴァレンタイン Varentain), is the last surviving member of the Last Hope and is now working with Double and Marie, the current Skullgirl. She was responsible for Painwheel's transformation under the direction of Brain Drain.",
                "Medium",
                "Rushdown"
            ),
            Fighter(
                "Double",
                R.drawable.doublew,
                "Double (JP: ダブル Daburu) is a shapeshifting creature working under the Trinity, responsible for ensuring that the Skull Heart falls into the \"right\" hands and manipulating events according to the goddesses' will. Her current guise is as a nun under the name Agatha Gloomfoil (JP: シスター・アガサ Shisutā Agasa). She is one of the primary antagonists of the game.",
                "Hard",
                "Composite"
            ),
            Fighter(
                "Squigly",
                R.drawable.squigly,
                "Sienna Contiello (JP: シエナ・コンティエロ Shiena Kontiero), more commonly known as Squigly (JP: スクィグリー Sukuigurī), is an undead girl, kept sentient thanks to her family's Parasite, Leviathan. Her parents were Roberto and Selene, the latter of whom was a previous Skullgirl.",
                "Medium",
                "Stance"
            )

        )

        adapter = FighterAdapter(this, fighters)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        searchBar.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val filteredList = fighters.filter {
                    it.name.contains(s.toString(), ignoreCase = true)
                }

                adapter.updateList(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        favoritesButton.setOnClickListener {

            val prefs = getSharedPreferences("codex_prefs", MODE_PRIVATE)

            if (!showingFavorites) {

                // SHOW FAVORITES
                val favoriteFighters = fighters.filter {

                    prefs.getBoolean("fav_${it.name}", false)
                }

                adapter.updateList(favoriteFighters)

                favoritesButton.text = "Show All"

                showingFavorites = true

            } else {

                // SHOW ALL
                adapter.updateList(fighters)

                favoritesButton.text = "Favorites"

                showingFavorites = false
            }
        }
    }
}