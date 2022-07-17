import authentication.Authentication;
import authentication.CredentialsService;
import authentication.PermissionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AutenticationTest {

    Authentication authenticationMock = Mockito.mock(Authentication.class);
    CredentialsService credentialsServiceMock = Mockito.mock(CredentialsService.class);
    PermissionService permissionServiceMock = Mockito.mock(PermissionService.class);

    @Test
    public void loginTest(){


        Mockito.when(credentialsServiceMock.isValidCredential("admin","123")).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission("admin")).thenReturn("CRUD");

        Authentication authentication = new Authentication();
        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);

        String expectedResult = "user authenticated successfully with permission: [CRUD]";
        String actualResult = authentication.login("admin","123");
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el usuario o password es incorrecto");

        Mockito.verify(credentialsServiceMock).isValidCredential("admin","123");
        Mockito.verify(permissionServiceMock).getPermission("admin");

    }

    /*@Test
    public void loginFailTest(){


        Mockito.when(credentialsServiceMock.isValidCredential("admin","12356")).thenReturn(false);
        Mockito.when(permissionServiceMock.getPermission("admin")).thenReturn("CRUD");

        Authentication authentication = new Authentication();
        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);

        String expectedResult = "user or password incorrect";
        String actualResult = authentication.login("admin","12356");
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el usuario o password es incorrecto");

        Mockito.verify(credentialsServiceMock).isValidCredential("admin","12356");
        Mockito.verify(permissionServiceMock).getPermission("admin");

    }*/
}
