package de.chillercode.deepdive.push

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import de.chillercode.deepdive.MainActivity
import de.chillercode.deepdive.R
import timber.log.Timber
import kotlin.random.Random


class DeepDiveMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Timber.i("RemoteMessage")
        Timber.i("\tdata: ${remoteMessage.data}")
        Timber.i("\tfrom: ${remoteMessage.from}")
        Timber.i("\tmessageId: ${remoteMessage.messageId}")
        Timber.i("\tmessageType: ${remoteMessage.messageType}")
        Timber.i("\tintent extras: ${remoteMessage.toIntent().extras}")


        sendNotification(remoteMessage)

    }

    private fun sendNotification(remoteMessage: RemoteMessage) {

        val pendingIntent = createPendingIntent()

        val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Infos available")
            )
            .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_LIGHTS)
            .setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            .setAutoCancel(true)
            .setContentTitle("News")
            .setContentIntent(pendingIntent)

        val manager = NotificationManagerCompat.from(applicationContext)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify(Random.nextInt(), notificationBuilder.build())
    }

    private fun createPendingIntent(): PendingIntent {
        return NavDeepLinkBuilder(this)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.notificationsDetailFragment)
            .createPendingIntent()
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
    }
}