import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import korlibs.image.bitmap.*
import kotlin.math.*
import korlibs.korge.view.*
import korlibs.korge.view.collision.*
import korlibs.time.*

class BasicAlien(val views: Views, image: Bitmap, val centerImg: Image) :
    CoroutineScope by CoroutineScope(Dispatchers.Default) {

    val spd = 100.0

    val basicAlienSprite = Sprite(image).apply {
        size(30.0, 30.0)
    }

    var x: Double = 0.0
    var y: Double = 0.0

    init {
        basicAlienSprite.addUpdater { dt ->
            val dx = (centerImg.x - basicAlienSprite.x + 30.0)
            val dy = (centerImg.y - basicAlienSprite.y + 30.0)
            val distance = sqrt(dx * dx + dy * dy)
            val unitDx = dx / distance
            val unitDy = dy / distance
            basicAlienSprite.xy(
                basicAlienSprite.x + unitDx * spd * dt.seconds,
                basicAlienSprite.y + unitDy * spd * dt.seconds
            )
            x = basicAlienSprite.x
            y = basicAlienSprite.y
            basicAlienSprite.onCollision({ it == centerImg }) {
                basicAlienSprite.removeFromParent()
            }
        }
    }
}
