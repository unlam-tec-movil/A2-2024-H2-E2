package ar.edu.unlam.mobile.scaffolding.ui.login.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R


@Composable
fun loginScreen(){
    Box(Modifier.fillMaxSize().padding(16.dp)){
        Login(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Login(modifier: Modifier) {
Column (modifier=modifier){
 HeaderImage()
  Spacer(modifier = modifier.padding(16.dp))
    EmailField()
}
}

@Composable
fun EmailField() {
    TextField(value = "", onValueChange = {})
}

@Composable
fun HeaderImage() {
   //Image(painter = painterResource(id= R.mipmap.ic_launcher),centerDescription="logo")
}
