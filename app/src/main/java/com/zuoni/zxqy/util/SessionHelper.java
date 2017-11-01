package com.zuoni.zxqy.util;

import android.content.Context;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.common.ui.popupmenu.NIMPopupMenu;
import com.netease.nim.uikit.common.ui.popupmenu.PopupMenuItem;
import com.netease.nim.uikit.common.util.sys.TimeUtil;
import com.netease.nim.uikit.custom.DefaultRecentCustomization;
import com.netease.nim.uikit.session.RecentCustomization;
import com.netease.nim.uikit.session.SessionCustomization;
import com.netease.nim.uikit.session.SessionEventListener;
import com.netease.nim.uikit.session.helper.MessageHelper;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.avchat.constant.AVChatRecordState;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatAttachment;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.List;

/**
 * UIKit自定义消息界面用法展示类
 */
public class SessionHelper {

    private static final int ACTION_HISTORY_QUERY = 0;
    private static final int ACTION_SEARCH_MESSAGE = 1;
    private static final int ACTION_CLEAR_MESSAGE = 2;

    private static SessionCustomization p2pCustomization;
    private static SessionCustomization teamCustomization;
    private static SessionCustomization myP2pCustomization;
    private static SessionCustomization robotCustomization;
    private static RecentCustomization recentCustomization;

    private static NIMPopupMenu popupMenu;
    private static List<PopupMenuItem> menuItemList;

    public static void init() {
        // 注册自定义消息附件解析器
//        NIMClient.getService(MsgService.class).registerCustomAttachmentParser(new CustomAttachParser());

        // 注册各种扩展消息类型的显示ViewHolder
        registerViewHolders();

        // 设置会话中点击事件响应处理
        setSessionListener();





        // 注册消息撤回监听器
        registerMsgRevokeObserver();

        NimUIKit.setCommonP2PSessionCustomization(null);
        NimUIKit.setCommonTeamSessionCustomization(null);

        NimUIKit.setRecentCustomization(getRecentCustomization());
    }




    public static void startTeamSession(Context context, String tid) {
        startTeamSession(context, tid, null);
    }

    public static void startTeamSession(Context context, String tid, IMMessage anchor) {
        NimUIKit.startTeamSession(context, tid, anchor);
    }




    private static RecentCustomization getRecentCustomization() {
        if (recentCustomization == null) {
            recentCustomization = new DefaultRecentCustomization() {
                @Override
                public String getDefaultDigest(RecentContact recent) {
                    switch (recent.getMsgType()) {
                        case avchat:
                            MsgAttachment attachment = recent.getAttachment();
                            AVChatAttachment avchat = (AVChatAttachment) attachment;
                            if (avchat.getState() == AVChatRecordState.Missed && !recent.getFromAccount().equals(NimUIKit.getAccount())) {
                                // 未接通话请求
                                StringBuilder sb = new StringBuilder("[未接");
                                if (avchat.getType() == AVChatType.VIDEO) {
                                    sb.append("视频电话]");
                                } else {
                                    sb.append("音频电话]");
                                }
                                return sb.toString();
                            } else if (avchat.getState() == AVChatRecordState.Success) {
                                StringBuilder sb = new StringBuilder();
                                if (avchat.getType() == AVChatType.VIDEO) {
                                    sb.append("[视频电话]: ");
                                } else {
                                    sb.append("[音频电话]: ");
                                }
                                sb.append(TimeUtil.secToTime(avchat.getDuration()));
                                return sb.toString();
                            } else {
                                if (avchat.getType() == AVChatType.VIDEO) {
                                    return ("[视频电话]");
                                } else {
                                    return ("[音频电话]");
                                }
                            }
                    }
                    return super.getDefaultDigest(recent);
                }
            };
        }

        return recentCustomization;
    }


    private static void registerViewHolders() {
//        NimUIKit.registerMsgItemViewHolder(FileAttachment.class, MsgViewHolderFile.class);
//        NimUIKit.registerMsgItemViewHolder(AVChatAttachment.class, MsgViewHolderAVChat.class);
//        NimUIKit.registerMsgItemViewHolder(GuessAttachment.class, MsgViewHolderGuess.class);
//        NimUIKit.registerMsgItemViewHolder(CustomAttachment.class, MsgViewHolderDefCustom.class);
//        NimUIKit.registerMsgItemViewHolder(StickerAttachment.class, MsgViewHolderSticker.class);
//        NimUIKit.registerMsgItemViewHolder(SnapChatAttachment.class, MsgViewHolderSnapChat.class);
//        NimUIKit.registerMsgItemViewHolder(RTSAttachment.class, MsgViewHolderRTS.class);
//        NimUIKit.registerTipMsgViewHolder(MsgViewHolderTip.class);
//        registerRedPacketViewHolder();
    }



    private static void setSessionListener() {
        SessionEventListener listener = new SessionEventListener() {
            @Override
            public void onAvatarClicked(Context context, IMMessage message) {
                // 一般用于打开用户资料页面
//                if (message.getMsgType() == MsgTypeEnum.robot && message.getDirect() == MsgDirectionEnum.In) {
//                    RobotAttachment attachment = (RobotAttachment) message.getAttachment();
//                    if (attachment.isRobotSend()) {
//                        RobotProfileActivity.start(context, attachment.getFromRobotAccount());
//                        return;
//                    }
//                }
//                UserProfileActivity.start(context, message.getFromAccount());
            }

            @Override
            public void onAvatarLongClicked(Context context, IMMessage message) {
                // 一般用于群组@功能，或者弹出菜单，做拉黑，加好友等功能
            }
        };

        NimUIKit.setSessionListener(listener);
    }






    private static void registerMsgRevokeObserver() {
        NIMClient.getService(MsgServiceObserve.class).observeRevokeMessage(new Observer<IMMessage>() {
            @Override
            public void onEvent(IMMessage message) {
                if (message == null) {
                    return;
                }

                MessageHelper.getInstance().onRevokeMessage(message);
            }
        }, true);
    }







}
