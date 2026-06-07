
import com.codandotv.streamplayerapp.core.permission.PermissionFactory
import com.codandotv.streamplayerapp.core.permission.PermissionsManager
import com.codandotv.streamplayerapp.core.permission.PermissionsManagerImpl
import org.koin.dsl.lazyModule

object PermissionsModule {
    val module = lazyModule {
        single<PermissionsManager> {
            PermissionsManagerImpl(
                controller = PermissionFactory()
                    .getPermissionFactory()
                    .createPermissionsController()
            )
        }
    }
}
