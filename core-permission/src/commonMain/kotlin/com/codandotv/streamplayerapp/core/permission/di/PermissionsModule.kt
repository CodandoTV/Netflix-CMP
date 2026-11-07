
import com.codandotv.streamplayerapp.core.permission.PermissionFactory
import com.codandotv.streamplayerapp.core.permission.PermissionsManager
import com.codandotv.streamplayerapp.core.permission.PermissionsManagerImpl
import org.koin.dsl.module

object PermissionsModule {
    val module = module {
        single<PermissionsManager> {
            PermissionsManagerImpl(
                controller = PermissionFactory()
                    .getPermissionFactory()
                    .createPermissionsController()
            )
        }
    }
}
