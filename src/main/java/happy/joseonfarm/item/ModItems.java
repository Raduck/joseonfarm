package happy.joseonfarm.item;

import happy.joseonfarm.Joseonfarm; // 메인 클래스 경로 확인!
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    // 1. 레지스트리 생성 (Spring의 ApplicationContext 같은 존재)
    // "지금부터 아이템들을 이 객체에 담아두었다가, 게임 켜질 때 한 번에 등록하겠다"는 뜻입니다.
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "joseonfarm"); // "joseonfarm"은 아까 정한 MOD ID

    // 2. 엽전(YEOPJEON) 아이템 등록
    // "yeopjeon"이라는 ID로 빈 껍데기 아이템을 등록합니다.
    public static final RegistryObject<Item> YEOPJEON = ITEMS.register("yeopjeon",
            () -> new Item(new Item.Properties()));

    // 3. 이벤트 버스 등록 메서드
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}