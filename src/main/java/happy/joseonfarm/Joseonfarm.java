package happy.joseonfarm;

import com.mojang.logging.LogUtils;
import happy.joseonfarm.item.ModCreativeModTabs;
import happy.joseonfarm.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// [참고] 나중에 예제 코드를 주석 해제(사용)할 때 필요한 임포트들입니다.
// import net.minecraft.core.registries.Registries;
// import net.minecraft.world.food.FoodProperties;
// import net.minecraft.world.item.BlockItem;
// import net.minecraft.world.item.CreativeModeTab;
// import net.minecraft.world.item.CreativeModeTabs;
// import net.minecraft.world.item.Item;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.state.BlockBehaviour;
// import net.minecraft.world.level.material.MapColor;
// import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
// import net.minecraftforge.registries.DeferredRegister;
// import net.minecraftforge.registries.ForgeRegistries;
// import net.minecraftforge.registries.RegistryObject;

@Mod(Joseonfarm.MODID)
public class Joseonfarm {

    public static final String MODID = "joseonfarm";
    private static final Logger LOGGER = LogUtils.getLogger();

    // =========================================================================================
    // [참고용 예제 코드 보관소] - 필요할 때 주석(/* ... */)을 풀어서 참고하세요.
    // =========================================================================================
    /*
    // 1. 레지스트리 저장소 생성 (블록, 아이템, 크리에이티브 탭)
    // DeferredRegister는 나중에 게임이 로딩될 때 한꺼번에 등록하기 위한 대기열 같은 것입니다.
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // 2. 예제 블록 등록 ("example_block")
    // 돌(Stone) 같은 색상을 가진 단순한 블록을 만듭니다.
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

    // 3. 예제 블록 아이템 등록 (BlockItem)
    // 블록만 있으면 인벤토리에 넣을 수 없습니다. '블록 아이템'을 등록해야 손에 들거나 설치할 수 있습니다.
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
            () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    // 4. 예제 음식 아이템 등록 ("example_item")
    // 먹을 수 있는 아이템 예제입니다. (nutrition: 배고픔 회복량, saturationMod: 포만감 유지력)
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build())));

    // 5. 예제 크리에이티브 탭 등록 ("example_tab")
    // 바닐라의 '전투(COMBAT)' 탭 앞에 우리 탭을 끼워넣습니다.
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab",
            () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance()) // 탭 아이콘 설정
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // 탭에 아이템 추가
            }).build());
    */
    // =========================================================================================

    public Joseonfarm() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 우리가 만든 'ModItems'와 'ModCreativeModTabs'를 등록합니다. (이건 활성화 상태)
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // =====================================================================================
        // [참고용 예제 등록 실행부] - 위에서 정의한 변수들을 실제로 게임에 로딩시키는 부분입니다.
        // =====================================================================================
        /*
        BLOCKS.register(modEventBus); // 블록 등록
        ITEMS.register(modEventBus);  // 아이템 등록
        CREATIVE_MODE_TABS.register(modEventBus); // 탭 등록

        // 'addCreative' 메서드를 리스너로 등록 (바닐라 탭에 아이템 넣기 기능)
        modEventBus.addListener(this::addCreative);
        */
        // =====================================================================================

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        // 예제 로그: 흙(Dirt) 블록의 정보를 콘솔에 찍어보는 코드
        // LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // =========================================================================================
    // [참고용 메서드] - 기존 마인크래프트 탭(예: 건축 블록 탭)에 내 아이템을 슬쩍 끼워넣고 싶을 때 씁니다.
    // =========================================================================================
    /*
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // '건축 블록' 탭을 열 때, 내 'EXAMPLE_BLOCK_ITEM'을 추가해라!
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }
    */

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}