package com.frisk.enterpriselaboration.Services;

import com.frisk.enterpriselaboration.Enteties.Address;
import com.frisk.enterpriselaboration.Enteties.Member;
import com.frisk.enterpriselaboration.Repositorys.AddressRepository;
import com.frisk.enterpriselaboration.Repositorys.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private AddressRepository addressRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Medlem hittades inte!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> addMember(Member member) {
        try {
            Long addressId = member.getAddress().getAddressId();
            Optional<Address> existingAddress = addressRepository.findById(addressId);

            if (existingAddress.isPresent()) {
                member.setAddress(existingAddress.get());
                Member savedMember = memberRepository.save(member);
                return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Adressen kunde inte hittas.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Kunde inte lägga till medlem. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteMember(Long id) {
        if(memberRepository.existsById(id)){
            memberRepository.deleteById(id);
            return new ResponseEntity<>("Medlem raderades!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Medlem hittades inte!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateMember(Member member) {
        if(memberRepository.existsById(member.getId())){
            Member updatedMember = memberRepository.save(member);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Medlem hittades inte!", HttpStatus.NOT_FOUND);
        }

    }
}
