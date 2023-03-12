package cz.upce.fei.cviceni01.graphql;

import cz.upce.fei.cviceni01.dto.AppUserDto;
import cz.upce.fei.cviceni01.dto.CreateAppUserInput;
import cz.upce.fei.cviceni01.service.AppUserService;
import cz.upce.fei.cviceni01.service.BadInputException;
import cz.upce.fei.cviceni01.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 * n + 1 problém je problém, který může nastat při zpracování graphql dotazů, které obsahují vnořené objekty,
 * pokud dotaz obsahuje tato pole, jako třeba seznam nějakých rolí uživatele, graphql vytvoří separátní graphql dotaz
 * pro každý prvek v seznamu, toto může vytvářet velké problémy ve výkonu.
 * Anotace @BatchQuery tomuto může zabránit tak, že získá seznam všech objektů pomocí jednoho dotazu najednou.
 *
 * @SchemaMapping se používá k definici chování pole pokud je dotazováno, jak transformovat data a jak zpracovávat chyby,
 * používá se pokud pole vrací jednu hodnotu (skalární nebo objekt)
 * @BatchMapping se používá k seskupení více prvků do jednoho souboru (batche), který vrací seznam všech objektů
 * <p>
 * <p>
 * GraphQL subscriptions umožňují klientům dostávat real-time aktualizace z graphql api když se data změní.
 * Klient se subscribne ke specifickému eventu u kterého chce dostávat aktualizace
 * a tím bude tyto aktualizace dostávat kdykoliv se tato data změní.
 * Příjemcem je uživatel odesílatel je GraphQL API.
 * Jako anotace se používá <ul>
 * <li>@GraphQLSubscription, což je anotace která označí metodu jako resolver pro daný subscription
 * definovaný v graphql</li>
 * <li> @GraphqlContext - tato anotace slouží k injectu kontextu do metody definované pomocí anotace výše.
 * Tato anotace se použije v parametru u výše definované metody a umožňuje nám získat přístup do aktuální websoketové
 * sessiony a získat další kontextové informace</li>
 * <li>@WebSocketSubscriptionMapping - umožňuje specifikovat websoketovou cestu k resolveru, který handluje graphql subscriptiony</li>
 * </ul>
 * Možné použití: uživatel dostane novou zprávu nebo email, který chceme tomuto uživateli ihned zobrazit
 * aplikace, která zobrazuje aktuální kurzy měn
 * multiplayerové hry - aktualizace skóre, pozice protihráčů
 * Subscription fungují pomocí Websoketového protokolu, je to jednorázové spojení které se naváže mezi klientem a serverem
 * , které umožňuje rychle poskytovat informace klientovi ze serveru bez nutnosti refreshe stránky
 */
@Controller
@AllArgsConstructor
public class AppUserQLController {

    private final AppUserService appUserService;
    private final CustomExceptionResolver customExceptionResolver;

    @QueryMapping
    public AppUserDto appUser(@Argument final Long id) throws ResourceNotFoundException {
        return appUserService.findById(id).toDto();
    }

    @MutationMapping
    public AppUserDto createAppUser(@Argument CreateAppUserInput input) throws BadInputException {
        if (input == null) {
            throw new BadInputException("input is null");
        }
        try {
            return appUserService.create(input.toAppUser()).toDto();
        } catch (Exception e) {
            throw new BadInputException(e.getMessage());
        }
    }

}
