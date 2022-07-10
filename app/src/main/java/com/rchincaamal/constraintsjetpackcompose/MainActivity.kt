package com.rchincaamal.constraintsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.rchincaamal.constraintsjetpackcompose.ui.theme.ConstraintsJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintSecondSample()
        }
    }
}

@Composable
fun ConstraintFirstSample() {
    val constraints = ConstraintSet { 
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val blueBox = createRefFor("blueBox")
        
        constrain(greenBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        
        constrain(redBox) {
            top.linkTo(greenBox.top, margin = 50.dp)
            start.linkTo(greenBox.start, margin = 50.dp)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(blueBox) {
            top.linkTo(redBox.top, margin = 50.dp)
            start.linkTo(redBox.start, margin = 50.dp)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
    }
    
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(Color.Green)
                .layoutId("greenBox")
        )

        Box(
            modifier = Modifier.background(Color.Red)
                .layoutId("redBox")
        )

        Box(
            modifier = Modifier.background(Color.Blue)
                .layoutId("blueBox")
        )
    }
}


@Composable
fun ConstraintSecondSample() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val blueBox = createRefFor("blueBox")
        val guideLine = createGuidelineFromStart(0.5f)

        constrain(greenBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(greenBox.bottom)
            start.linkTo(guideLine)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(blueBox) {
            top.linkTo(redBox.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createVerticalChain(greenBox, redBox, blueBox)
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(Color.Green)
                .layoutId("greenBox")
        )

        Box(
            modifier = Modifier.background(Color.Red)
                .layoutId("redBox")
        )

        Box(
            modifier = Modifier.background(Color.Blue)
                .layoutId("blueBox")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConstraintSecondSample()
}