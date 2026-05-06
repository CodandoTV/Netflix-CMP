
import com.codandotv.streamplayerapp.core_permission.permission.PermissionsManager
import org.koin.dsl.lazyModule

object PermissionsModule {
    val module = lazyModule {
        single<PermissionsManager> {
            com.codandotv.streamplayerapp.core_permission.permission.PermissionsManagerImpl(
                controller = com.codandotv.streamplayerapp.core_permission.permission.PermissionFactory()
                    .getPermissionFactory()
                    .createPermissionsController()
            )
        }
    }
}
