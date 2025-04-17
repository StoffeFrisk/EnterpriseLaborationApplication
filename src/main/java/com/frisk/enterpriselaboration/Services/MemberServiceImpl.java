package com.frisk.enterpriselaboration.Services;

import com.frisk.enterpriselaboration.Enteties.Address;
import com.frisk.enterpriselaboration.Enteties.Member;
import com.frisk.enterpriselaboration.Repositorys.AddressRepository;
import com.frisk.enterpriselaboration.Repositorys.MemberRepository;
import com.frisk.enterpriselaboration.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        return new ResponseEntity<>(member, HttpStatus.OK);
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
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));

        Address address = member.getAddress();

        memberRepository.deleteById(id);

        List<Member> othersWithSameAddress  = memberRepository.findAll().stream()
                .filter(m-> m.getAddress().getAddressId() == address.getAddressId())
                .collect(Collectors.toList());

        if(othersWithSameAddress.isEmpty()) {
            addressRepository.deleteById(address.getAddressId());
        }
        return new ResponseEntity<>("Medlem raderad!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateMember(Member member) {
        Member existingMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", member.getId()));

        existingMember.setFirstName(member.getFirstName());
        existingMember.setLastName(member.getLastName());
        existingMember.setEmail(member.getEmail());
        existingMember.setPhone(member.getPhone());
        existingMember.setDateOfBirth(member.getDateOfBirth());
        existingMember.setAddress(member.getAddress());

        Member updatedMember = memberRepository.save(existingMember);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);


    }
}
