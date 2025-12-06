package com.example.a20210820choiyein

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class ResultActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)
        title = "투표 결과"

        // 인텐트 데이터 수신
        val voteResult = intent.getIntArrayExtra("VoteCount") ?: return
        val imageName = intent.getStringArrayExtra("ImageName") ?: return

        // 1. 이미지 리소스 ID 배열 생성 (문제의 요구사항)
        val imageFileId = arrayOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        )

        // 9개의 TextView, RatingBar ID 배열
        val tvIDs = arrayOf(R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9)
        val rbarIDs = arrayOf(R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9)

        // 2. 가장 많은 표를 받은 그림 찾기
        var maxPos = 0
        for (i in voteResult.indices) {
            if (voteResult[i] > voteResult[maxPos]) {
                maxPos = i
            }
        }

        // 3. 1등 그림과 제목 표시 (상단 View 연결)
        val tvTop = findViewById<TextView>(R.id.tvTop)
        val ivTop = findViewById<ImageView>(R.id.ivTop)

        tvTop.text = imageName[maxPos]
        ivTop.setImageResource(imageFileId[maxPos])


        // 4. 각 TextView 및 RatingBar에 투표 결과 반영
        for (i in voteResult.indices) {
            findViewById<TextView>(tvIDs[i]).text = imageName[i]
            findViewById<RatingBar>(rbarIDs[i]).rating = voteResult[i].toFloat()
        }

        findViewById<Button>(R.id.btnReturn).setOnClickListener {
            finish()
        }
    }
}