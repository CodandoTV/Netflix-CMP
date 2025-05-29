import UIKit
import BackgroundTasks
import streamplayerapp

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {

        NotifierManager.shared.initialize(configuration: NotificationPlatformConfigurationIos(
                showPushNotification: true,
                askNotificationPermissionOnStart: true,
                notificationSoundName: nil
            )
        )


        BGTaskScheduler.shared.register(forTaskWithIdentifier: "com.codandotv.streamplayerapp", using: nil) { task in
            self.handleSyncWork(task: task as! BGProcessingTask)
        }
        print("Tarefa registrada!")

        scheduleSyncWork()
        return true
    }

    func scheduleSyncWork() {
        let request = BGProcessingTaskRequest(identifier: "com.codandotv.streamplayerapp")
        request.requiresNetworkConnectivity = false
        request.requiresExternalPower = false
        request.earliestBeginDate = Date(timeIntervalSinceNow: 5 * 60) // 5 minutos

        do {
            try BGTaskScheduler.shared.submit(request)
            print("Tarefa agendada com sucesso para pelo menos: \(request.earliestBeginDate!)")
        } catch {
            print("Falha ao agendar tarefa: \(error.localizedDescription)")
        }


    }

    func handleSyncWork(task: BGProcessingTask) {
        scheduleSyncWork()

        let queue = OperationQueue()
        queue.maxConcurrentOperationCount = 1

        let operation = BlockOperation {
            print("Tarefa executando operação de sync...")
            SyncBridge.shared.syncData {
                print("Tarefa do rodando no iOS")
                task.setTaskCompleted(success: true)
            }
        }

        task.expirationHandler = {
            print("Tarefa expirada.")
            queue.cancelAllOperations()
        }

        queue.addOperation(operation)
    }
}
