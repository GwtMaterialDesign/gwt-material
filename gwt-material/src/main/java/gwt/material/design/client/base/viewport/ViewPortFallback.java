package gwt.material.design.client.base.viewport;

/**
 * View Port Fallback.
 * <br><br>
 * Return true to continue propagating view port detection, false to break.
 * <br><br>
 * Note that if {@link ViewPortHandler#propagateFallback(boolean)} is not true,
 * this will only be called if no {@link Resolution} matches are made.
 * Otherwise the fallback is called upon every failed {@link Resolution} match
 * and can be propagated (or not propagated) with its call result.
 */
public interface ViewPortFallback {

    /**
     * Fallback call.
     *
     * @param viewPort the view port that wasn't detected.
     * @return true to continue propagating view port detection, false to break.
     */
    boolean call(ViewPortRect viewPort);
}
