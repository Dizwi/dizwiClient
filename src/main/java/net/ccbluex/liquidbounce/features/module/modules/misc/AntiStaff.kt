/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/SkidderMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.FDPClient
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.WorldEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.utils.misc.HttpUtils
import net.ccbluex.liquidbounce.features.value.BoolValue
import net.ccbluex.liquidbounce.features.value.TextValue
import net.minecraft.network.play.server.S14PacketEntity
import net.minecraft.network.play.server.S1DPacketEntityEffect

@ModuleInfo(name = "AntiStaff", category = ModuleCategory.MISC)
object AntiStaff : Module() {

    private val serversText = TextValue("Servers", "")
    
    private val blocksMCValue = BoolValue("BlocksMC", true)
    private val jartexValue = BoolValue("Jartex", false)
    private val pikaValue = BoolValue("Pika", true)
    private val mineboxValue = BoolValue("Minebox", false)
    private val hycraftValue = BoolValue("Hycraft", true)
    private val librecraftValue = BoolValue("Librecraft", false)
    private val universocraftValue = BoolValue("Universocraft", true)
    private val customValue = BoolValue("Custom", false)
    
    private val notifyValue = BoolValue("Notification",true)
    private val chatValue = BoolValue("SendChatMessage",false)
    private val messageValue = TextValue("Message", "%staff% was detected as a staff member!").displayable { chatValue.get() }
    private val customURLValue = TextValue("CustomURL", "https://raw.githubusercontent.com/fdpweb/fdpweb.github.io/main/test").displayable { customValue.get() }

    private val leaveValue = BoolValue("Leave",true)
    private val leaveMessageValue = TextValue("LeaveCommand","/hub").displayable { leaveValue.get() }
    
    private var bmcStaff : String = "M4rwaan72074 1F5aMH___3oo22775 1Daykel19648 M7mmd14801 Muntadher14800 ZANAD13047 MK_F1612784 Maarcii11574 Boviix9781 iiRaivy9367 iS3od_7847 qB6o67605 1M7mdz7306 1Hussain7158 xMz76905 iikimo6226 iMehdi_6010 1M7mmD5685 saad65603 MightyM7MD5248 1Sweet4557 EyesO_Diamond4490 Ev2n4170 Aboz3bl3756 lt1x3356 iAhmedGG3291 Thenvra3218 Y2men3118 3Mmr3081 1RealFadi2996 Requieem2664 CutieRana2633 1LaB2345 Bastic2320 SeniorAziz2242 xL2d2155 BaSiL_1232087 Flineer2083 phxnomenal1984 BinRashood1981 leeleeeleeeleee1980 Yaazzeed1974 Luvaa1938 yQuack1897 w7r1882 1Narwhql1871 xIMonster_Rj1868 wzii1782 420syr1a1763 ixBander1753 7re2a_YT1727 WalriderTime1692 1M0ha1675 1Levaai1666 PT71650 xanaxjuice1615 Tibbz_BGamer1543 DeeRx1513 1Mshari1510 uh8e1416 1HeyImHasson_1380 TheDaddyJames1299 ilybb01268 1L7NN1267 s2lm1232 AbduIlah1205 SalemBayern_1199 1Ahmvd1181 Mythiques1137 Fta71117 KingHOYT1061 baderr1027 G3rryx1021 S3rvox1004 Ravnly974 mzh973 Driction972 F2rris966 1Tz3bo962 yosife_7Y928 FaRidok924 Blood_Artz918 RADVN914 OttomanGrqndson913 1A7mad1871 BlackOurs867 FastRank846 AbuA7md506791 Y_04784 JustDrink_754 Bo6lalll735 Creegam729 iRxv724 Abdulaziz187721 Lunching718 mokgii715 Dizibre711 91l7704 Refolt690 ImM7MAD686 Ittekimasu664 0Aix653 Demon_001649 Dqrkfall646 Sadlly638 N15_624 qPito616 ImortalWqlkeR589 _N3580 1LoST_568 wl3d562 xiiRadi560 c22l554 imOgLoo553 xiDayzer545 ebararh539 GoldenGapples525 1Sinqx521 H2ris516 KaaReeeM515 Saajed514 Tostiebramkaas508 DetectiveFahad507 vxom506 1ForAGer487 1Kweng467 manuelmaster464 ritclaw454 1Loga_451 Sp0tzy_433 itzZa1D421 SirMedo_419 arbawii409 xLePerfect396 EVanDoskI394 Banderr389 Mondoros384 iSolom378 0Da3s377 FexoraNEP371 3au369 Jrx7368 Raceth367 MindOfNasser365 3AmOdi_361 ImXann360 1RE3354 90fa345 be6sho340 _SpecialSA_333 vdhvm325 Neeres319 JustKreem317 aXav307 SpecialAdam_304 NotMoHqMeD__296 RealA7md287 8mhh277 Zqvies270 TheDrag_Xx270 2gfs268 nopals265 0hPqnos261 Ba1z260 1KhaleeD259 Onyc_259 AfootDiamond117243 RealWayne241 DarkA5_240 INFAMOUSEEE234 inVertice230 rixw1229 IR3DX226 0h_Roby223 Futurezii223 Veshan221 beyondviolets220 PotatoSoublaki219 SamoXS215 1Sweatly215 D1ZZY0NE214 DetectiveFoxTY213 ToFy_206 Tibbz_BGamer_206 m7mdxjw203 1Adam1_203 Aymann_200 DaBabyFan193 1Pepe_189 _R3188 rivez181 BinDontCare179 vinnythebot178 Megtit_Imad177 EZKarDIOPalma175 1_aq170 TheOnlyM7MAD164 Just7MO161 Draggn_144 OldAlone141 sh5boo6135 _Vxpe131 xDmg127 iA11124 _Surfers_122 Mark_Gamer_YT_114 yff3114 1_ST110 zAhmd102 Tabby_Bhau101 mliodse101 BigZ3tr96 STEEEEEVEEEE90 _b_i83 3zez73 rqnkk69 3rodi66 d5qq66 ogm64 k6en58 iHmKiller57 IF3MH57 LRYD55 needheer53 Aiiden52 Jarxay52 iiEsaTKing_44 hsva44 xXBXLTXx44 InjjectoR44 RealSG41 zCroDanger39 redcriper36 deathally34 vM6r34 Viva_1Vinci33 _xayu_32 1DeVilz31 Serena_66631 Tetdeus30 _z2_29 xa7a26 mshye26 vBursT_25 1Omxr23 qMabel22 real_wd21 Changerr6921 Cryslinq20 lacvna19 iLuvSG_19 Alaam_FG19 ByeByeByeBye18 _obx18 7MZH16 TheHicat15 lareey15 SpecialAdel_15 kostasidk15 xDiaa_levo15 OREOBZ14 Kuhimitsu14 AFG_progamer9213 Bo3aShor13 BigBossGhost12 IDoubIe12 9we12 AwKTaM11 _iSkyla11 i_Ym511 abd0_3698 _Jqmxs8 xIIMnt7r7 unhealthily7 DangPavel7 HM___7 Millsap7 mamdoh6 cW6n6 tverdy6 IxKimo5 IuseiLezs5 mshyz4 IxDjole4 1Derex3 Lemfs2 Oxenaa2 Mjdra_call_ME2 PinCabbage320742 Its_HighNoon1 zayedk0 xDupzHell0 Wesccar0 obaida1234450 bota_690 refractionDuvo0 AhmedPROGG0 X_Mah_X0 M1M_0 A5oShnBaT0 cuz_himo0 Du7ym0 This Week iAhmedGG228 1F5aMH___3oo173 1KhaleeD155 s2lm125 uh8e101 zAhmd75 Bo6lalll62 OttomanGrqndson60 IF3MH56 needheer53 1HeyImHasson_48 Jarxay45 iHmKiller42 InjjectoR42 GoldenGapples36 Flineer35 hsva34 G3rryx32 redcriper31 xiDayzer31 Tetdeus29 Serena_66629 Lunching28 _z2_23 yosife_7Y22 F2rris22 wzii21 ilybb020 2gfs20 qMabel20 real_wd20 aXav19 1DeVilz18 RealSG18 1Omxr18 _obx18 0hPqnos16 7MZH16 ogm15 vM6r14 nopals14 STEEEEEVEEEE14 Dqrkfall14 ByeByeByeBye14 lacvna13 kostasidk12 Futurezii12 Viva_1Vinci12 k6en12 Demon_00111 zCroDanger10 vBursT_10 i_Ym510 _xayu_9 Bo3aShor9 AFG_progamer929 Refolt9 1Kweng8 abd0_3698 Requieem8 mokgii7 HM___7 iiRaivy7 mshye7 Saajed7 DeeRx7 Alaam_FG7 mamdoh6 Kuhimitsu6 cW6n6 90fa5 inVertice5 unhealthily5 lareey5 Millsap5 xDmg5 rqnkk4 tverdy4 IxKimo4 AwKTaM4 1Derex3 d5qq3 TheDaddyJames2 Mjdra_call_ME2 IDoubIe2 JustDrink_2 BinDontCare2 wl3d2 mshyz2 Changerr691 EZKarDIOPalma1 Tibbz_BGamer1 NotMoHqMeD__1 Banderr1 Driction1 yff31 CutieRana1 Abdulaziz1871 DetectiveFahad1 IxDjole1 1_aq1 lt1x1 iSolom1 0Aix1 xIIMnt7r1 Tostiebramkaas1 xanaxjuice1 rixw11 xiiRadi0 SeniorAziz0 m7mdxjw0 qPito0 TheDrag_Xx0 _Surfers_0 Boviix0 LRYD0 iA110 1Tz3bo0 MK_F160 M4rwaan0 1Adam1_0 _Jqmxs0 Aboz3bl0 Lemfs0 imOgLoo0 Luvaa0 1Narwhql0 deathally0 arbawii0 DaBabyFan0 8mhh0 AbuA7md5060 ImXann0 1LaB0 PT70 Mondoros0 Y_040 vdhvm0 1Mshari0 Bastic0 1Sweet0 1Daykel0 vxom0 _Vxpe0 ebararh0 OREOBZ0 AfootDiamond1170 BigBossGhost0 Thenvra0 IR3DX0 xMz70 Mark_Gamer_YT_0 Y2men0 Just7MO0 Creegam0 iikimo0 Sp0tzy_0 leeleeeleeeleee0 beyondviolets0 1M7mmD0 FexoraNEP0 Fta70 BigZ3tr0 xL2d0 3zez0 1Ahmvd0 PotatoSoublaki0 0Da3s0 1_ST0 phxnomenal0 Oxenaa0 RealWayne0 JustKreem0 ToFy_0 Its_HighNoon0 MightyM7MD0 1M7mdz0 0h_Roby0 Raceth0 BlackOurs0 Aymann_0 mzh0 1Pepe_0 iS3od_0 KaaReeeM0 Jrx70 be6sho0 saad60 1L7NN0 91l70 Ittekimasu0 Ba1z0 1RE30 1LoST_0 TheHicat0 IuseiLezs0 1A7mad10 vinnythebot0 _b_i0 iRxv0 1Sweatly0 EyesO_Diamond0 Tabby_Bhau0 M7mmd0 INFAMOUSEEE0 Maarcii0 Dizibre0 3AmOdi_0 3rodi0 MindOfNasser0 rivez0 Veshan0 3au0 baderr0 1RealFadi0 _R30 1M0ha0 AbduIlah0 xDiaa_levo0 ixBander0 1Loga_0 Megtit_Imad0 Mythiques0 9we0 SalemBayern_0 Sadlly0 RealA7md0 ImortalWqlkeR0 N15_0 ritclaw0 sh5boo60 SpecialAdam_0 WalriderTime0 w7r0 c22l0 Zqvies0 BaSiL_1230 BinRashood0 TheOnlyM7MAD0 _N30 D1ZZY0NE0 EVanDoskI0 Draggn_0 DarkA5_0 SirMedo_0 ZANAD0 OldAlone0 _SpecialSA_0 mliodse0 1Hussain0 Tibbz_BGamer_0 iLuvSG_0 S3rvox0 Ev2n0 xLePerfect0 iMehdi_0 SpecialAdel_0 1ForAGer0 ImM7MAD0 420syr1a0 1Levaai0 Blood_Artz0 FastRank0 itzZa1D0 Yaazzeed0 1Sinqx0 xa7a0 KingHOYT0 iiEsaTKing_0 RADVN0 H2ris0 3Mmr0 Aiiden0 qB6o60 Muntadher0 DangPavel0 Cryslinq0 _iSkyla0 PinCabbage320740 Onyc_0 Neeres0 xIMonster_Rj0 SamoXS0 DetectiveFoxTY0 Ravnly0 manuelmaster0 FaRidok0 xXBXLTXx0 7re2a_YT0 yQuack0 zayedk0 xDupzHell0 Wesccar0 obaida1234450 bota_690 refractionDuvo0 AhmedPROGG0 X_Mah_X0 M1M_0 A5oShnBaT0 cuz_himo0 Du7ym0 This Month M4rwaan13268 ZANAD5357 Boviix3150 iAhmedGG2322 M7mmd1443 s2lm806 Flineer754 uh8e748 Mythiques741 Fta7687 OttomanGrqndson646 w7r589 saad6557 G3rryx547 wzii528 Ittekimasu467 EyesO_Diamond451 91l7409 Aboz3bl378 1Daykel357 H2ris328 Neeres319 PT7301 1L7NN289 BlackOurs288 1Sweet274 Muntadher257 3au240 Thenvra240 1F5aMH___3oo239 itzZa1D236 1RE3232 1Ahmvd226 1Kweng222 JustDrink_209 Ba1z206 1Narwhql204 manuelmaster203 0Aix202 SirMedo_197 F2rris193 phxnomenal192 imOgLoo192 TheDaddyJames188 1Tz3bo185 AbuA7md506184 Tibbz_BGamer183 leeleeeleeeleee183 Luvaa182 Jrx7182 c22l179 RADVN179 0hPqnos179 iSolom179 SpecialAdam_176 ilybb0175 Requieem173 1Loga_173 Sadlly172 mzh169 baderr168 0Da3s168 ebararh167 rixw1167 IR3DX163 1RealFadi163 Zqvies163 SalemBayern_161 RealWayne160 beyondviolets159 1KhaleeD158 CutieRana157 ixBander156 KaaReeeM156 xanaxjuice156 ImXann155 lt1x155 iiRaivy155 Banderr154 xLePerfect153 Veshan152 Bastic152 RealA7md150 1ForAGer150 wl3d149 Yaazzeed149 1A7mad1148 0h_Roby147 Demon_001146 yosife_7Y146 3Mmr144 mokgii142 WalriderTime141 vdhvm140 Refolt139 AfootDiamond117139 Onyc_139 TheDrag_Xx137 INFAMOUSEEE135 Raceth132 ToFy_131 arbawii131 iS3od_127 SamoXS126 Aymann_125 iMehdi_124 Dizibre123 Megtit_Imad122 Blood_Artz120 Y2men119 KingHOYT117 _R3110 1_aq109 BinRashood109 MK_F16108 NotMoHqMeD__107 7re2a_YT106 BaSiL_123105 1Sweatly102 zAhmd102 Mondoros101 vxom100 1M7mdz100 iRxv98 FaRidok98 yQuack98 JustKreem96 D1ZZY0NE96 Creegam95 Tostiebramkaas95 1Pepe_94 _Vxpe93 1M0ha92 xL2d91 MightyM7MD91 Draggn_91 Lunching90 sh5boo690 STEEEEEVEEEE90 N15_86 DaBabyFan86 1LaB86 ImM7MAD84 FexoraNEP83 FastRank81 S3rvox80 rqnkk69 Bo6lalll68 ritclaw66 Mark_Gamer_YT_66 xMz766 xIMonster_Rj65 iikimo65 Ev2n65 EZKarDIOPalma65 1Sinqx62 Sp0tzy_62 _N361 Tibbz_BGamer_60 Abdulaziz18760 1_ST59 420syr1a59 k6en58 DetectiveFahad57 iHmKiller57 IF3MH57 1HeyImHasson_53 TheOnlyM7MAD53 needheer53 1M7mmD52 Jarxay52 Ravnly51 Just7MO47 aXav45 iiEsaTKing_44 rivez44 InjjectoR44 hsva44 1Levaai43 xiiRadi42 RealSG41 zCroDanger39 xiDayzer38 GoldenGapples37 redcriper36 DarkA5_34 vM6r34 deathally34 1Hussain33 Viva_1Vinci33 _xayu_32 Maarcii31 1DeVilz31 Serena_66631 Tetdeus30 _z2_29 iA1128 3AmOdi_27 Tabby_Bhau27 mshye26 vBursT_25 m7mdxjw24 LRYD24 1Omxr23 2gfs22 qMabel22 real_wd21 Changerr6921 lacvna19 Alaam_FG19 _obx18 ByeByeByeBye18 90fa17 ogm16 BinDontCare16 7MZH16 nopals15 TheHicat15 lareey15 kostasidk15 xDiaa_levo15 SpecialAdel_15 Dqrkfall14 Kuhimitsu14 Futurezii14 Bo3aShor13 AFG_progamer9213 OldAlone13 9we12 IDoubIe12 BigBossGhost12 AwKTaM11 i_Ym511 _iSkyla11 Saajed9 abd0_3698 _b_i8 _Jqmxs8 xIIMnt7r7 Millsap7 DangPavel7 DeeRx7 unhealthily7 HM___7 mamdoh6 cW6n6 inVertice6 tverdy6 IxKimo5 IuseiLezs5 xDmg5 mshyz4 qPito4 IxDjole4 1Derex3 qB6o63 xXBXLTXx3 d5qq3 Lemfs2 1Adam1_2 Mjdra_call_ME2 yff32 Aiiden2 PinCabbage320742 vinnythebot2 Oxenaa2 Its_HighNoon1 Driction1 3zez1 PotatoSoublaki1 ImortalWqlkeR1 DetectiveFoxTY1 AbduIlah1 3rodi1 Y_040 MindOfNasser0 1Mshari0 be6sho0 BigZ3tr0 xa7a0 EVanDoskI0 Cryslinq0 mliodse0 8mhh0 _Surfers_0 1LoST_0 _SpecialSA_0 OREOBZ0 SeniorAziz0 iLuvSG_0 zayedk0 xDupzHell0 Wesccar0 obaida1234450 bota_690 refractionDuvo0 AhmedPROGG0 X_Mah_X0 M1M_0 A5oShnBaT0 cuz_himo0 Du7ym"
    private var jartexStaff : String = "voodootje0 Max Rodagave Wrath JustThiemo Andeh Nirahz stupxd Botervrij Viclyn_  DrogonMC ovq Flexier NotLoLo1818 SabitTSDM07 ItzCqldFxld Laux  bene_e  iFlyYT          HeadsBreker       AX79       Technostein          Djim       Serpentsalamce       Almostlikeaboss       JustAtaman       ZoneRGH       naranbaatr       louiekeys       Difficulted       FuzniX       xHasey       sammyxt       CR7811149       Xerrainrin       toastt_x          UpperGround       Swervinng       SquareWings928       Yanique1       pakitonia     Stxrs".toString()
    private var pikaStaff : String = "Max voodootje0 MrFrenco JustThiemo Wrath Andeh Nirahz stupxd Botervrij Subvalent Apo2xd Arrly Minecraft_leg CaptainGeoGR Thijme01 ChickenDinnr Crni_ MrGownz Outscale MrEpiko Crveni_Marlboro zMqrcc _Stella_xD Stormidity TryToHitMe Alparo_ CandyOP Astrospeh TinCanL TheTrueNova FIKOZ DarkVenom7 caila5 Lpkfvip i9BAR "
    private var mineboxStaff : String = "xSp3ctro_  SaF3rC  Sagui  TheSuperXD_YT  xAnibal  xTheKillex25x  HankWalter  JavierFenix  inothayami  ChaosSoleil  ElChamo300  Robert TO1010  itachi_uchiha_s  roku__  rynne_ sushi dashi Vicky_21"
    private var zonecraftStaff : String = "002Aren asiessoydecono donerreMC elMagnificPvP ErCris fernxndx gourd Gudaa ImAle ImMarvolo ismq nicoxrm pacorro rapheos MrBara MrMonkey57 uploadedhh trifeyy 002Aren Agu5 augusmaster BetTD d411 dunshbey85 ElMaGnific Pv ErCris Eugene FelmaxMC Gudaa ¡Enux ImMarvolo sleepless ismq ItzOmar16 joescam LuisPoMC Nicoxrm pacorro "
    private var hycraftStaff : String = "Alexander245 arqui Blandih Chony_15 jac0mc Ragen06 TheBryaan TMT_131 Yapecito MartynaGamer830 archeriam"
    private var librecraftStaff : String = "Kudos  H0DKIER  Iker_XD9  acreate  iJeanSC  acreate  Janet  Rosse_RM  aldoum23neko_  DERGO  MJKINGPAND"
    private var universocraftStaff : String = "0edx_ 0_Lily 1Kao denila  fxrchus  haaaaaaaaaaax_ iBlackSoulz iMxon_ JuliCarles kvvwro Tauchet wSilv6r _JuPo_"
    
    
    private var detected = false
    private var staffs = ""
    
    
    @EventTarget
    fun onWorld(event: WorldEvent) {
        staffs = ""
        if (blocksMCValue.get()) staffs = staffs + " " + bmcStaff
        if (jartexValue.get()) staffs = staffs + " " + jartexStaff
        if (pikaValue.get()) staffs = staffs + " " + pikaStaff
        if (mineboxValue.get()) staffs = staffs + " " + mineboxStaff
        if (hycraftValue.get()) staffs = staffs + " " + hycraftStaff
        if (librecraftValue.get()) staffs = staffs + " " + librecraftStaff
        if (universocraftValue.get()) staffs = staffs + " " + universocraftStaff
        if (customValue.get()) {
        
            try {
                staffs = staffs + " " + HttpUtils.get(customURLValue.get())

                FDPClient.hud.addNotification(Notification("AntiStaff", "SuccessFully Loaded URL", NotifyType.SUCCESS, 1000))
            } catch (err: Throwable) {
                FDPClient.hud.addNotification(Notification("AntiStaff", "Error when loading URL", NotifyType.ERROR, 1000))
                println(err)
            }
        }
            
        detected = false
    }

    @EventTarget
    fun onPacket(event: PacketEvent){
        if (mc.theWorld == null || mc.thePlayer == null) return

        val packet = event.packet // smart convert
        if (packet is S1DPacketEntityEffect) {
            val entity = mc.theWorld.getEntityByID(packet.entityId)
            if (entity != null && (staffs.contains(entity.name) || staffs.contains(entity.displayName.unformattedText))) {
                if (!detected) {
                    if (notifyValue.get()){
                        FDPClient.hud.addNotification(Notification(name, "Detected staff members with invis. You should quit ASAP.", NotifyType.WARNING, 8000))
                    }
                    
                    if (chatValue.get()) {
                        mc.thePlayer.sendChatMessage((messageValue.get()).replace("%staff%", entity.name))
                    }
                    if (leaveValue.get()) {
                        mc.thePlayer.sendChatMessage(leaveMessageValue.get())
                    }
                    
                    detected = true
                }
            }
        }
        if (packet is S14PacketEntity) {
            val entity = packet.getEntity(mc.theWorld)

            if (entity != null && (staffs.contains(entity.name) || staffs.contains(entity.displayName.unformattedText))) {
                if (!detected) {
                    if (notifyValue.get()){
                    FDPClient.hud.addNotification(Notification(name, "Detected staff members. You should quit ASAP.", NotifyType.WARNING,8000))
                    }
                    
                    if (chatValue.get()) {
                        ClientUtils.displayChatMessage((messageValue.get()).replace("%staff%", entity.name))
                    }
                    
                    if (leaveValue.get()) {
                        mc.thePlayer.sendChatMessage(leaveMessageValue.get())
                    }
                    
                    detected = true
                }
            }
        }
    }
}
