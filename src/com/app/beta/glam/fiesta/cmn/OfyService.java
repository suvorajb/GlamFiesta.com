package com.app.beta.glam.fiesta.cmn;

import com.app.beta.glam.fiesta.db.TopLooksDO;
import com.app.beta.glam.fiesta.db.TopStreetStyleDO;
import com.app.beta.glam.fiesta.db.FeaturedUsersDO;
import com.app.beta.glam.fiesta.db.GlobalTagsDO;
import com.app.beta.glam.fiesta.db.InterviewDO;
import com.app.beta.glam.fiesta.db.LoginHistoryDO;
import com.app.beta.glam.fiesta.db.LookbookCommentsDO;
import com.app.beta.glam.fiesta.db.RecentVisitsDO;
import com.app.beta.glam.fiesta.db.UserActivitiesDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserFollowerDO;
import com.app.beta.glam.fiesta.db.UserGiveawayWinnerDO;
import com.app.beta.glam.fiesta.db.UserGlamPointsActivitiesDO;
import com.app.beta.glam.fiesta.db.UserLoginResetDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.db.UserLookbookTagsDO;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;


public class OfyService {
	// Register our models with Objectify. If you add a new model, make sure to
	// register it here as well.
	static {
		factory().register(UserActivitiesDO.class);
		factory().register(UserDO.class);
		factory().register(UserFollowerDO.class);
		factory().register(LoginHistoryDO.class);
		factory().register(UserLoginResetDO.class);
		factory().register(UserLookbookDO.class);
		factory().register(LookbookCommentsDO.class);
		factory().register(UserGlamPointsActivitiesDO.class);
		factory().register(FeaturedUsersDO.class);
		factory().register(TopStreetStyleDO.class);
		factory().register(UserLookbookTagsDO.class);
		factory().register(UserGiveawayWinnerDO.class);
		factory().register(RecentVisitsDO.class);
		factory().register(GlobalTagsDO.class);
		factory().register(TopLooksDO.class);
		factory().register(InterviewDO.class);
	}

	/**
	 * @return Objectify instance to use for datastore interaction.
	 */
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	/**
	 * @return Factory for Objectify instances.
	 */
	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
