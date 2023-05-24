package likelion.springbootnyong.controller;

import likelion.springbootnyong.domain.Member;
import likelion.springbootnyong.service.MemberService;
import likelion.springbootnyong.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * Controller는 클라이언트로부터 요청을 받아들이고, 해당 요청에 대한 처리를 수행한 후에 응답을 반환하는 역할을 합니다. 이를 통해 클라이언트와 서버 간의 상호작용을 가능하게 합니다.
 Controller 메서드는 @RequestMapping 어노테이션을 사용하여 특정 URL과 HTTP 메서드와의 매핑을 지정할 수 있습니다.
 @RequestMapping("members")는 해당 메서드 또는 클래스가 "/members"라는 URL에 대한 요청을 처리한다는 것을 나타냅니다. 이 경우, 메서드 또는 클래스는 "/members"라는 경로로 들어오는 HTTP 요청을 처리할 수 있습니다.
 public class는 컨트롤러 클래스를 정의하는 코드입니다.
 **/
@Controller
@RequestMapping("members")
public class MemberController {
    /*
     * MemberService라는 클래스의 인스턴스를 참조하는 private final 멤버 변수를 선언하는 코드입니다.
     * MemberService는 일반적으로 비즈니스 로직을 처리하는 서비스 클래스입니다. MemberService 인스턴스를 memberService 변수에 할당하여 의존성 주입(Dependency Injection)을 수행합니다.
     * private 키워드는 해당 멤버 변수가 클래스 내에서만 접근 가능하다는 의미입니다. 따라서 이 변수는 MemberController 클래스 내부에서만 사용할 수 있습니다.
     * final 키워드는 변수가 선언과 동시에 초기화되고, 이후에는 그 값을 변경할 수 없음을 의미합니다. 즉, memberService 변수는 한 번 초기화된 이후에는 다른 MemberService 인스턴스를 참조할 수 없습니다.
     *
     **/
    private final MemberService memberService;

    /*
     * @Autowired는 Spring 프레임워크에서 의존성 주입(Dependency Injection)을 수행하기 위한 어노테이션입니다.
     * 생성자는 MemberServiceImpl 타입의 객체를 파라미터로 받아서 MemberController 클래스의 멤버 변수인 memberService에 할당하는 역할을 합니다. memberService 멤버 변수는 MemberController 클래스 내에서 해당 인스턴스를 참조하기 위해 사용됩니다.
     * 따라서, MemberController 객체가 생성될 때 Spring은 MemberServiceImpl 타입의 객체를 찾아서 자동으로 생성하고, 해당 객체를 MemberController의 생성자를 통해 주입합니다.
     **/
    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberService = (MemberService) memberServiceImpl;
    }

    /*
     * @GetMapping("new")은 해당 메서드가 "/new" 경로로 들어오는 GET 요청을 처리함을 나타냅니다.
     * createForm 메서드는 클라이언트가 "/new" 경로로 GET 요청을 보냈을 때 호출되어 처리됩니다.
     * 메서드 내부에서는 model.addAttribute("memberForm", new Member())을 통해 "memberForm"이라는 이름으로 새로운 Member 객체를 Model에 추가합니다. 이는 뷰로 전달되어 뷰에서 해당 객체에 접근할 수 있게 됩니다. 일반적으로 뷰에서 입력 필드를 생성하고 사용자의 입력을 받을 수 있도록 합니다.
     * return "members/createMemberForm"은 뷰의 이름을 반환합니다. 이는 뷰 리졸버(View Resolver)를 통해 실제 뷰를 찾아 렌더링하는 데 사용됩니다.
     **/
    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/createMemberForm";
    }

    /*
     * @PostMapping("new")는 해당 메서드가 "/new" 경로로 들어오는 POST 요청을 처리함을 나타냅니다.
     * POST 요청을 처리하기 위한 핸들러 메서드인 create는 클라이언트가 "/new" 경로로 POST 요청을 보냈을 때 호출되어 처리됩니다.
     * 메서드 내부에서는 memberService.save(member)를 호출하여 MemberService를 통해 전달받은 Member 객체를 저장합니다. 이는 MemberService의 save 메서드를 호출하여 데이터베이스나 다른 저장소에 해당 멤버를 저장하는 기능을 수행합니다.
     * return "redirect:/"은 클라이언트를 다른 경로로 리다이렉션시키는 역할을 합니다. 여기서는 "/" 경로로 리다이렉션되어 홈 페이지 또는 기본 페이지로 이동하게 됩니다.
     **/
    @PostMapping("new")
    public String create(Member member) {
        memberService.save(member);
        return "redirect:/";
    }

    /*
     * @GetMapping("")은 해당 메서드가 루트 경로("/")로 들어오는 GET 요청을 처리함을 나타냅니다.
     * GET 요청을 처리하기 위한 핸들러 메서드인 findAll은 클라이언트가 루트 경로("/")로 GET 요청을 보냈을 때 호출되어 처리됩니다.
     * 메서드 내부에서는 memberService.findAll()을 호출하여 MemberService를 통해 모든 멤버를 조회한 후 memberList라는 이름으로 Model에 추가합니다. 이는 뷰로 전달되어 뷰에서 해당 객체에 접근할 수 있게 됩니다. 일반적으로 조회된 데이터를 화면에 표시하기 위해 사용됩니다.
     * return "members/memberList"은 뷰의 이름을 반환합니다. 이는 뷰 리졸버(View Resolver)를 통해 실제 뷰를 찾아 렌더링하는 데 사용됩니다. 예를 들어, "members/memberList"라는 뷰 이름을 반환하면, 실제로는 해당 뷰 템플릿 파일인 "memberList.html"이나 "memberList.jsp" 등이 렌더링됩니다.
     **/
    @GetMapping("")
    public String findAll(Model model) {
        List<Member> memberList = (List<Member>) memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }
}
