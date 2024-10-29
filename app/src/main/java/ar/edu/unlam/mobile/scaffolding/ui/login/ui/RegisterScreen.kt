package ar.edu.unlam.mobile.scaffolding.ui.login.ui
import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Compiler.disable


@Composable
fun loginScreen(){
    Box(Modifier.fillMaxSize().padding(16.dp)){
        Login(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Login(modifier: Modifier) {
Column (modifier=modifier){
 //HeaderImage(modifier.align(Alignment.CenterHorizontally))
  Spacer(modifier = modifier.padding(16.dp))
    NameUserField()
    Spacer(modifier = modifier.padding(16.dp))
    EmailField()
    Spacer(modifier = modifier.padding(16.dp))
    PassWordField()
    Spacer(modifier = modifier.padding(16.dp))
    PassWordField()
    RegisterBotton()

}
}
@Preview
@Composable
fun RegisterBotton() {
    Button(onClick = {},
        modifier=Modifier.fillMaxWidth().height(40.dp)),
    Color = ButtonDefaults.buttonColors(
        backgroundColor=Color(0xFFFF4303),
    disableBackgroundColor=Color(0xFFF78058),
    contentColor= Color.Blue
    disableContentColor=Color.Cyan
    ){
        Text(text="Registrarse")
    }
}

@Preview
@Composable
fun NameUserField() {
    TextField(value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text="Nombre de Usuario") },
        label = { Text("Nombre de Usuario") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        interactionSource = MutableInteractionSource(),
    )
}

@Preview
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun PassWordField() {
    TextField(value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text="Password") },
        label = { Text("Password") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        interactionSource = MutableInteractionSource(),
    )

}
@Preview
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun EmailField() {
    TextField(value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text="Email") },

        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        interactionSource = MutableInteractionSource(),
    )
}

/*@Composable
fun HeaderImage(modifier: Modifier) {
   Image(painter = painterResource(id= R.mipmap.ic_launcher), contentDescription = "Header")
}*/
