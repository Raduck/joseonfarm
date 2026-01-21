package happy.joseonfarm.item;

import happy.joseonfarm.Joseonfarm;
import happy.joseonfarm.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "joseonfarm");

    public static final RegistryObject<CreativeModeTab> JOSEON_TAB = CREATIVE_MODE_TABS.register("joseon_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.YEOPJEON.get())) // 탭 아이콘을 엽전으로 설정
                    .title(Component.translatable("itemGroup.joseonfarm_tab")) // 아까 lang 파일에 적은 이름
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.YEOPJEON.get()); // 여기에 아이템 추가
                        // 나중에 아이템 늘어나면 pOutput.accept(ModItems.DOKKAEBI_CLUB.get()); 이런 식으로 추가
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}