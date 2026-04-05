
import com.codandotv.streamplayerapp.core_permission.permission.PermissionsManager
import org.koin.dsl.module

object PermissionsModule {
    val module = module {
        single<PermissionsManager> {
            com.codandotv.streamplayerapp.core_permission.permission.PermissionsManagerImpl(
                controller = com.codandotv.streamplayerapp.core_permission.permission.PermissionFactory()
                    .getPermissionFactory()
                    .createPermissionsController()
            )
        }
    }
}
