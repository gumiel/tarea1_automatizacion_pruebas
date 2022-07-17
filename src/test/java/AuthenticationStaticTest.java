import authenticationStatic.Authentication;
import authentication.CredentialsService;
import authenticationStatic.CredentialsStaticService;
import authenticationStatic.PermissionStaticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class AuthenticationStaticTest {


    @Test
    public void loginTest(){
        MockedStatic<CredentialsStaticService> objectCredentialMocked = Mockito.mockStatic(CredentialsStaticService.class);
        MockedStatic<PermissionStaticService> objectPermissionMocked = Mockito.mockStatic(PermissionStaticService.class);

        objectCredentialMocked.when(()->CredentialsStaticService.isValidCredential("admin", "123")).thenReturn(true);
        objectPermissionMocked.when(()->PermissionStaticService.getPermission("admin")).thenReturn("CRUD");

        Authentication authentication = new Authentication();

        String expectedResult = "user authenticated successfully with permission: [CRUD]";
        String actualResult = authentication.login("admin","123");

        Assertions.assertEquals(expectedResult, actualResult, "ERROR el usuario o password es incorrecto");

        objectCredentialMocked.close();
        objectPermissionMocked.close();
    }

    /*@Test
    public void loginFailTest(){
        MockedStatic<CredentialsStaticService> objectCredentialMocked = Mockito.mockStatic(CredentialsStaticService.class);
        MockedStatic<PermissionStaticService> objectPermissionMocked = Mockito.mockStatic(PermissionStaticService.class);

        objectCredentialMocked.when(()->CredentialsStaticService.isValidCredential("admin", "123456")).thenReturn(false);
        objectPermissionMocked.when(()->PermissionStaticService.getPermission("admin")).thenReturn("CRUD");

        Authentication authentication = new Authentication();

        String expectedResult = "user or password incorrect";
        String actualResult = authentication.login("admin","123456");

        Assertions.assertEquals(expectedResult, actualResult, "ERROR el usuario o password es incorrecto");

        objectCredentialMocked.close();
        objectPermissionMocked.close();
    }*/

}
